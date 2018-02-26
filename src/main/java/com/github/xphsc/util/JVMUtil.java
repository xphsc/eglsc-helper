package com.github.xphsc.util;

import com.github.xphsc.exception.ExceptionUtil;
import com.github.xphsc.io.StreamUtil;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * 有关 <code>JVM</code> 处理的工具类。
 * Created by ${huipei.x} on 2017-5-31.
 */
public class JVMUtil {

    private static final String[] MANIFESTS = { "Manifest.mf", "manifest.mf", "MANIFEST.MF" };

    private static final JavaRuntimeInfo JAVA_RUNTIME_INFO = new JavaRuntimeInfo();

    public static boolean appendToClassPath(String name) {
        if (!FileUtil.exist(name)) {
            return false;
        }
        try {
            ClassLoader clsLoader = ClassLoader.getSystemClassLoader();
            Method appendToClassPathMethod =
                    clsLoader.getClass().getDeclaredMethod("appendToClassPathForInstrumentation", String.class);

            if (null != appendToClassPathMethod) {
                appendToClassPathMethod.setAccessible(true);
                appendToClassPathMethod.invoke(clsLoader, name);
            }

            return true;
        } catch (Exception e) {
            throw ExceptionUtil.toRuntimeException(e);
        }
    }


    public static String[] addAllJarsToClassPath(String dirName) {
        if (!FileUtil.isDirectory(dirName)) {
            return null;
        }

        File dir = new File(dirName);
        List<String> ret = CollectionUtil.newArrayList();

        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                ret.addAll(Arrays.asList(addAllJarsToClassPath(file.getAbsolutePath())));
                continue;
            }

            if (file.getName().toLowerCase().endsWith(".jar") && appendToClassPath(file.getAbsolutePath())) {
                ret.add(file.getAbsolutePath());
            }
        }

        return ret.toArray(new String[0]);
    }

    private static Manifest getManifestFromFile(File classpathItem) {

        File metaDir = new File(classpathItem, "META-INF");
        File manifestFile = null;
        if (metaDir.isDirectory()) {
            for (String m : MANIFESTS) {
                File mFile = new File(metaDir, m);
                if (mFile.isFile()) {
                    manifestFile = mFile;
                    break;
                }
            }
        }

        if (manifestFile == null) {
            return null;
        }

        return getAndClose(manifestFile);
    }

    // FIXME ExceptionUtil.toRuntimeException(e) And method name
    private static Manifest getAndClose(File manifestFile) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(manifestFile);
            return new Manifest(fis);
        } catch (IOException e) {
            throw ExceptionUtil.toRuntimeException(e);
        } finally {
            StreamUtil.close(fis);
        }
    }

    private static Manifest getManifestFromJar(File classpathItem) {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(classpathItem);
            return new JarFile(classpathItem).getManifest();
        } catch (IOException e) {
            throw new RuntimeException("JVMUtil.getManifestFromJar Error", e);
        } finally {
            StreamUtil.close(fis);
        }

    }

    public static Manifest getManifest(File classpathItem) {
        if (classpathItem == null) {
            return null;
        }

        if (classpathItem.isFile()) {
            return getManifestFromJar(classpathItem);
        }

        return getManifestFromFile(classpathItem);
    }

    private static String getClasspathItemBaseDir(File classpathItem) {

        if (classpathItem.isFile()) {
            return classpathItem.getParent();
        }

        return classpathItem.toString();
    }




    public static File[] getClasspath(ClassLoader classLoader) {
        Set<File> classpaths = CollectionUtil.newHashSet();

        while (classLoader != null) {
            if (classLoader instanceof URLClassLoader) {
                URL[] urls = ((URLClassLoader) classLoader).getURLs();
                for (URL u : urls) {
                    File f = FileUtil.toFile(u);
                    if (f != null) {
                        try {
                            f = f.getCanonicalFile();
                            classpaths.add(f);
                            addInnerClasspathItems(classpaths, f);
                        } catch (IOException e) {
                            throw ExceptionUtil.toRuntimeException(e);
                        }
                    }
                }
            }
            classLoader = classLoader.getParent();
        }

        String bootstrap = JAVA_RUNTIME_INFO.SUN_BOOT_CLASS_PATH;
        if (bootstrap != null) {
            classpaths.add(new File(bootstrap));
        }

        return classpaths.toArray(new File[classpaths.size()]);
    }

    private static void addInnerClasspathItems(Set<File> classpaths, File item) throws IOException {

        Manifest manifest = getManifest(item);
        if (manifest == null) {
            return;
        }

        Attributes attributes = manifest.getMainAttributes();
        if (attributes == null) {
            return;
        }

        String classPaths = attributes.getValue(Attributes.Name.CLASS_PATH);
        if (classPaths == null) {
            return;
        }

        String base = getClasspathItemBaseDir(item);

        String[] tokens = StringUtil.split(classPaths, ' ');
        for (String t : tokens) {
            File file = new File(base, t);
            file = file.getCanonicalFile();

            if (file.exists()) {
                classpaths.add(file);
            }
        }
    }

    /**
     * 取得当前运行的JRE的信息。
     *
     * @return <code>JreInfo</code>对象
     */
    public static final JavaRuntimeInfo getJavaRuntimeInfo() {
        return JAVA_RUNTIME_INFO;
    }

    /**
     * 代表当前运行的JRE的信息。
     */
    public static final class JavaRuntimeInfo {

        private final String SUN_BOOT_CLASS_PATH = getSystemProperty("sun.boot.class.path", false);

        private final String SUN_ARCH_DATA_MODEL = getSystemProperty("sun.arch.data.model", false);

        private final String JAVA_VENDOR_URL = getSystemProperty("java.vendor.url", false);

        /**
         * 防止从外界创建此对象。
         */
        private JavaRuntimeInfo() {
        }
        public final String getSunBootClassPath() {
            return SUN_BOOT_CLASS_PATH;
        }

        /**
         * There's no public API that allows you to distinguish between 32 and
         * <p>
         * 64-bit operation. Think of 64-bit as just another platform in the
         * <p>
         * write once, run anywhere tradition. However, if you'd like to write
         * <p>
         * code which is platform specific (shame on you), the system property
         * <p>
         * sun.arch.data.model has the value "32", "64", or "unknown".
         *
         * @return <code>32</code> or <code>64</code>-bit operation,if not support the property, return
         *         <code>unknown</code>
         */
        public final String getSunArchDataModel() {
            return SUN_ARCH_DATA_MODEL;
        }

        public final String getVendorURL() {
            return JAVA_VENDOR_URL;
        }

    }

    /**
     * 取得系统属性，如果因为Java安全的限制而失败，则将错误打在<code>System.err</code>中，然后返回 <code>null</code>。
     *
     * @param name 属性名
     * @param quiet 安静模式，不将出错信息打在<code>System.err</code>中
     *
     * @return 属性值或<code>null</code>
     */
    private static String getSystemProperty(String name, boolean quiet) {
        try {
            return System.getProperty(name);
        } catch (SecurityException e) {
            if (!quiet) {
                System.err.println("Caught a SecurityException reading the system property '" + name
                        + "'; the SystemUtil property value will default to null.");
            }

            return null;
        }
    }
}
