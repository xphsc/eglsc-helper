package com.eglsc.lang;

import com.eglsc.util.CharsetUtil;
import com.eglsc.util.ClassUtils;
import com.eglsc.util.StringUtil;
import com.eglsc.util.URLUtil;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by ${huipei.x} on 2017-5-25.
 */
public class ClassScaner {

    private static FileFilter fileFilter = new FileFilter() {
        public boolean accept(File pathname) {
            return ClassScaner.isClass(pathname.getName()) || pathname.isDirectory() || ClassScaner.isJarFile(pathname);
        }
    };

    private ClassScaner() {
    }

    public static Set<Class<?>> scanPackageByAnnotation(String packageName, final Class<? extends Annotation> annotationClass) {
        return scanPackage(packageName, new Filter() {
            public boolean accept(Object var1) {
                return false;
            }

            public boolean accept(Class<?> clazz) {
                return clazz.isAnnotationPresent(annotationClass);
            }
        });
    }

    public static Set<Class<?>> scanPackageBySuper(String packageName, final Class<?> superClass) {
        return scanPackage(packageName, new Filter() {
            public boolean accept(Object var1) {
                return false;
            }

            public boolean accept(Class<?> clazz) {
                return superClass.isAssignableFrom(clazz) && !superClass.equals(clazz);
            }
        });
    }

    public static Set<Class<?>> scanPackage() {
        return scanPackage("", (Filter)null);
    }

    public static Set<Class<?>> scanPackage(String packageName) {
        return scanPackage(packageName, (Filter)null);
    }

    public static Set<Class<?>> scanPackage(String packageName, Filter<Class<?>> classFilter) {
        if(StringUtil.isBlank(packageName)) {
            packageName = "";
        }

        packageName = getWellFormedPackageName(packageName);
        HashSet classes = new HashSet();
        Set classPaths = ClassUtils.getClassPaths(packageName);
        Iterator javaClassPaths = classPaths.iterator();

        while(javaClassPaths.hasNext()) {
            String arr$ = (String)javaClassPaths.next();
            arr$ = URLUtil.decode(arr$, CharsetUtil.systemCharset());
            fillClasses(arr$, packageName, classFilter, classes);
        }

        if(classes.isEmpty()) {
            String[] var9 = ClassUtils.getJavaClassPaths();
            String[] var10 = var9;
            int len$ = var9.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String classPath = var10[i$];
                classPath = URLUtil.decode(classPath, CharsetUtil.systemCharset());
                fillClasses(classPath, new File(classPath), packageName, classFilter, classes);
            }
        }

        return classes;
    }

    private static String getWellFormedPackageName(String packageName) {
        return packageName.lastIndexOf(".") != packageName.length() - 1?packageName + ".":packageName;
    }

    private static void fillClasses(String path, String packageName, Filter<Class<?>> classFilter, Set<Class<?>> classes) {
        int index = path.lastIndexOf(".jar!");
        if(index != -1) {
            path = path.substring(0, index + ".jar".length());
            path = StringUtil.removePrefix(path, "file:");
            processJarFile(new File(path), packageName, classFilter, classes);
        } else {
            fillClasses(path, new File(path), packageName, classFilter, classes);
        }

    }

    private static void fillClasses(String classPath, File file, String packageName, Filter<Class<?>> classFilter, Set<Class<?>> classes) {
        if(file.isDirectory()) {
            processDirectory(classPath, file, packageName, classFilter, classes);
        } else if(isClassFile(file)) {
            processClassFile(classPath, file, packageName, classFilter, classes);
        } else if(isJarFile(file)) {
            processJarFile(file, packageName, classFilter, classes);
        }

    }

    private static void processDirectory(String classPath, File directory, String packageName, Filter<Class<?>> classFilter, Set<Class<?>> classes) {
        File[] arr$ = directory.listFiles(fileFilter);
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            File file = arr$[i$];
            fillClasses(classPath, file, packageName, classFilter, classes);
        }

    }

    private static void processClassFile(String classPath, File file, String packageName, Filter<Class<?>> classFilter, Set<Class<?>> classes) {
        if(!classPath.endsWith(File.separator)) {
            classPath = classPath + File.separator;
        }

        String path = file.getAbsolutePath();
        if(StringUtil.isEmpty(packageName)) {
            path = StringUtil.removePrefix(path, classPath);
        }

        String filePathWithDot = path.replace(File.separator, ".");
        boolean subIndex = true;
        int subIndex1;
        if((subIndex1 = filePathWithDot.indexOf(packageName)) != -1) {
            int endIndex = filePathWithDot.lastIndexOf(".class");
            String className = filePathWithDot.substring(subIndex1, endIndex);
            fillClass(className, packageName, classes, classFilter);
        }

    }

    private static void processJarFile(File file, String packageName, Filter<Class<?>> classFilter, Set<Class<?>> classes) {
        try {
            Iterator ex = Collections.list((new JarFile(file)).entries()).iterator();

            while(ex.hasNext()) {
                JarEntry entry = (JarEntry)ex.next();
                if(isClass(entry.getName())) {
                    String className = entry.getName().replace("/", ".").replace(".class", "");
                    fillClass(className, packageName, classes, classFilter);
                }
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    private static void fillClass(String className, String packageName, Set<Class<?>> classes, Filter<Class<?>> classFilter) {
        if(className.startsWith(packageName)) {
            try {
                Class ex = Class.forName(className, false, ClassUtils.getClassLoader());
                if(classFilter == null || classFilter.accept(ex)) {
                    classes.add(ex);
                }
            } catch (Exception var5) {
                ;
            }
        }

    }

    private static boolean isClassFile(File file) {
        return isClass(file.getName());
    }

    private static boolean isClass(String fileName) {
        return fileName.endsWith(".class");
    }

    private static boolean isJarFile(File file) {
        return file.getName().endsWith(".jar");
    }
}
