package com.eglsc.lang;

import com.eglsc.util.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ${huipei.x} on 2017-5-25.
 */
public final class Singleton {

    private static Map<Class<?>, Object> pool = new ConcurrentHashMap();

    private Singleton() {
    }

    public static <T> T get(Class<T> clazz, Object... params) {
        Object obj = pool.get(clazz);
        if(null == obj) {
            Class var3 = Singleton.class;
            synchronized(Singleton.class) {
                obj = pool.get(clazz);
                if(null == obj) {
                    obj = ClassUtils.newInstance(clazz, params);
                    pool.put(clazz, obj);
                }
            }
        }

        return (T) obj;
    }

    public static <T> T get(String className, Object... params) {
        Class clazz = ClassUtils.loadClass(className);
        return (T) get(clazz, params);
    }

    public static void remove(Class<?> clazz) {
        pool.remove(clazz);
    }

    public static void destroy() {
        pool.clear();
    }
}
