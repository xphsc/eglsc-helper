package com.github.xphsc.util;

import com.github.xphsc.exception.UtilException;
import com.github.xphsc.lang.ClassScaner;
import com.github.xphsc.lang.Filter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Created by ${huipei.x} on 2017-5-25.
 */
public class ClassUtil {

    private ClassUtil() {
    }

    public static Class<?>[] getClasses(Object... objects) {
        Class[] classes = new Class[objects.length];

        for(int i = 0; i < objects.length; ++i) {
            classes[i] = objects[i].getClass();
        }

        return classes;
    }
    public static boolean isInstance(Object obj, Class<?> klass) {
        return null == klass?false:klass.isInstance(obj);
    }

    public static Set<Class<?>> scanPackageByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return ClassScaner.scanPackageByAnnotation(packageName, annotationClass);
    }

    public static Set<Class<?>> scanPackageBySuper(String packageName, Class<?> superClass) {
        return ClassScaner.scanPackageBySuper(packageName, superClass);
    }

    public static Set<Class<?>> scanPackage() {
        return ClassScaner.scanPackage();
    }

    public static Set<Class<?>> scanPackage(String packageName) {
        return ClassScaner.scanPackage(packageName);
    }

    public static Set<Class<?>> scanPackage(String packageName, Filter<Class<?>> classFilter) {
        return ClassScaner.scanPackage(packageName, classFilter);
    }

    public static Set<String> getPublicMethodNames(Class<?> clazz) {
        HashSet methodSet = new HashSet();
        Method[] methodArray = getPublicMethods(clazz);
        Method[] arr$ = methodArray;
        int len$ = methodArray.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Method method = arr$[i$];
            String methodName = method.getName();
            methodSet.add(methodName);
        }

        return methodSet;
    }

    public static Method[] getPublicMethods(Class<?> clazz) {
        return clazz.getMethods();
    }

    public static List<Method> getPublicMethods(Class<?> clazz, Filter<Method> filter) {
        if(null == clazz) {
            return null;
        } else {
            Method[] methods = getPublicMethods(clazz);
            ArrayList methodList;
            if(null != filter) {
                methodList = new ArrayList();
                Method[] arr$ = methods;
                int len$ = methods.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    Method method = arr$[i$];
                    if(filter.accept(method)) {
                        methodList.add(method);
                    }
                }
            } else {
                methodList = CollectionUtil.newArrayList(methods);
            }

            return methodList;
        }
    }





    public static Method getPublicMethod(Class<?> clazz, String methodName, Class... paramTypes) throws NoSuchMethodException, SecurityException {
        try {
            return clazz.getMethod(methodName, paramTypes);
        } catch (NoSuchMethodException var4) {
            return getDeclaredMethod(clazz, methodName, paramTypes);
        }
    }

    public static Set<String> getDeclaredMethodNames(Class<?> clazz) {
        HashSet methodSet = new HashSet();
        Method[] methodArray = getDeclaredMethods(clazz);
        Method[] arr$ = methodArray;
        int len$ = methodArray.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Method method = arr$[i$];
            String methodName = method.getName();
            methodSet.add(methodName);
        }

        return methodSet;
    }

    public static Method[] getDeclaredMethods(Class<?> clazz) {
        HashSet methodSet;
        for(methodSet = new HashSet(); null != clazz; clazz = clazz.getSuperclass()) {
            Method[] declaredMethods = clazz.getDeclaredMethods();
            Method[] arr$ = declaredMethods;
            int len$ = declaredMethods.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Method method = arr$[i$];
                methodSet.add(method);
            }
        }

        return (Method[])methodSet.toArray(new Method[methodSet.size()]);
    }

    public static Method getDeclaredMethodOfObj(Object obj, String methodName, Object... args) throws NoSuchMethodException, SecurityException {
        return getDeclaredMethod(obj.getClass(), methodName, getClasses(args));
    }

    public static Method getDeclaredMethod(Class<?> clazz, String methodName, Class... parameterTypes) throws NoSuchMethodException, SecurityException {
        Method method = null;

        while(null != clazz) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
                break;
            } catch (NoSuchMethodException var5) {
                clazz = clazz.getSuperclass();
            }
        }

        return method;
    }

    public static boolean isEqualsMethod(Method method) {
        if(method != null && method.getName().equals("equals")) {
            Class[] paramTypes = method.getParameterTypes();
            return paramTypes.length == 1 && paramTypes[0] == Object.class;
        } else {
            return false;
        }
    }

    public static boolean isHashCodeMethod(Method method) {
        return method != null && method.getName().equals("hashCode") && method.getParameterTypes().length == 0;
    }

    public static boolean isToStringMethod(Method method) {
        return method != null && method.getName().equals("toString") && method.getParameterTypes().length == 0;
    }

    public static Set<String> getClassPathResources() {
        return getClassPaths("");
    }

    public static Set<String> getClassPaths(String packageName) {
        String packagePath = packageName.replace(".", "/");

        Enumeration resources;
        try {
            resources = getClassLoader().getResources(packagePath);
        } catch (IOException var4) {
            throw new UtilException(StringUtil.format("Loading classPath [{}] error!", new Object[]{packagePath}), var4);
        }

        HashSet paths = new HashSet();

        while(resources.hasMoreElements()) {
            paths.add(((URL)resources.nextElement()).getPath());
        }

        return paths;
    }

    public static String getClassPath() {
        return getClassPathURL().getPath();
    }

    public static URL getClassPathURL() {
        return getURL("");
    }

    public static URL getURL(String resource) {
        return getClassLoader().getResource(resource);
    }

    public static String[] getJavaClassPaths() {
        String[] classPaths = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
        return classPaths;
    }

    public static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static ClassLoader getClassLoader() {
        ClassLoader classLoader = getContextClassLoader();
        if(classLoader == null) {
            classLoader = ClassUtil.class.getClassLoader();
            if(null == classLoader) {
                classLoader = ClassLoader.getSystemClassLoader();
            }
        }

        return classLoader;
    }



    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception var2) {
            throw new UtilException(StringUtil.format("Instance class [{}] error!", new Object[]{clazz}), var2);
        }
    }

    public static <T> T newInstance(Class<T> clazz, Object... params) {
        if(ArrayUtil.isEmpty(params)) {
            return newInstance(clazz);
        } else {
            Class[] paramTypes = getClasses(params);
            Constructor constructor = getConstructor(clazz, getClasses(params));
            if(null == constructor) {
                throw new UtilException("No Constructor matched for parameter types: [{}]", new Object[]{paramTypes});
            } else {
                try {
                    return getConstructor(clazz, paramTypes).newInstance(params);
                } catch (Exception var5) {
                    throw new UtilException(StringUtil.format("Instance class [{}] error!", new Object[]{clazz}), var5);
                }
            }
        }
    }

    public static <T> Constructor<T> getConstructor(Class<T> clazz, Class... parameterTypes) {
        if(null == clazz) {
            return null;
        } else {
            Constructor[] constructors = clazz.getConstructors();
            Constructor[] arr$ = constructors;
            int len$ = constructors.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Constructor constructor = arr$[i$];
                Class[] pts = constructor.getParameterTypes();
                if(isAllAssignableFrom(pts, parameterTypes)) {
                    return constructor;
                }
            }

            return null;
        }
    }

    public static boolean isAllAssignableFrom(Class<?>[] types1, Class<?>[] types2) {
        if(ArrayUtil.isEmpty(types1) && ArrayUtil.isEmpty(types2)) {
            return true;
        } else if(types1.length == types2.length) {
            for(int i = 0; i < types1.length; ++i) {
                if(!types1[i].isAssignableFrom(types2[i])) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public static <T> Class<T> loadClass(String className, boolean isInitialized) {
        try {
            Class clazz = Class.forName(className, isInitialized, getClassLoader());
            return clazz;
        } catch (ClassNotFoundException var4) {
            throw new UtilException(var4);
        }
    }

    public static <T> Class<T> loadClass(String className) {
        return loadClass(className, true);
    }
















    public static boolean isPublic(Class<?> clazz) {
        if(null == clazz) {
            throw new NullPointerException("Class to provided is null.");
        } else {
            return Modifier.isPublic(clazz.getModifiers());
        }
    }

    public static boolean isPublic(Method method) {
        if(null == method) {
            throw new NullPointerException("Method to provided is null.");
        } else {
            return isPublic(method.getDeclaringClass());
        }
    }

    public static boolean isNotPublic(Class<?> clazz) {
        return !isPublic(clazz);
    }

    public static boolean isNotPublic(Method method) {
        return !isPublic(method);
    }

    public static boolean isStatic(Method method) {
        return Modifier.isStatic(method.getModifiers());
    }

    public static Method setAccessible(Method method) {
        if(null != method && isNotPublic(method)) {
            method.setAccessible(true);
        }

        return method;
    }

    public static boolean isAbstract(Class<?> clazz) {
        return Modifier.isAbstract(clazz.getModifiers());
    }

    public static boolean isNormalClass(Class<?> clazz) {
        return null != clazz && !clazz.isInterface() && !isAbstract(clazz) && !clazz.isEnum() && !clazz.isArray() && !clazz.isAnnotation() && !clazz.isSynthetic() && !clazz.isPrimitive();
    }

    public static Class<?> getTypeArgument(Class<?> clazz) {
        return getTypeArgument(clazz, 0);
    }

    public static Class<?> getTypeArgument(Class<?> clazz, int index) {
        Type superType = clazz.getGenericSuperclass();
        if(superType instanceof ParameterizedType) {
            ParameterizedType genericSuperclass = (ParameterizedType)superType;
            Type[] types = genericSuperclass.getActualTypeArguments();
            if(null != types && types.length > index) {
                Type type = types[index];
                if(type instanceof Class) {
                    return (Class)type;
                }
            }
        }

        return null;
    }

    public static Object getPrimitiveDefaultValue(Class<?> primitiveType) {
        return Boolean.TYPE.equals(primitiveType)?Boolean.valueOf(false):(Byte.TYPE.equals(primitiveType)?Byte.valueOf((byte)0):(Character.TYPE.equals(primitiveType)?Character.valueOf('\u0000'):(Short.TYPE.equals(primitiveType)?Short.valueOf((short)0):(Integer.TYPE.equals(primitiveType)?Integer.valueOf(0):(Long.TYPE.equals(primitiveType)?Long.valueOf(0L):(Float.TYPE.equals(primitiveType)?Float.valueOf(0.0F):(Double.TYPE.equals(primitiveType)?Double.valueOf(0.0D):null)))))));
    }
    public static String getPackage(Class<?> clazz) {
        if(clazz == null) {
            return "";
        } else {
            String className = clazz.getName();
            int packageEndIndex = className.lastIndexOf(".");
            return packageEndIndex == -1?"":className.substring(0, packageEndIndex);
        }
    }

    public static String getPackagePath(Class<?> clazz) {
        return getPackage(clazz).replace('.', '/');
    }
}
