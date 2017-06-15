package com.eglsc.exception;

import com.eglsc.io.ByteArrayOutputStream;
import com.eglsc.util.CollectionUtils;
import com.eglsc.util.StringUtil;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${huipei.x} on 2017-5-25.
 */
public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static String getMessage(Throwable e) {
        return StringUtil.format("{}: {}", new Object[]{e.getClass().getSimpleName(), e.getMessage()});
    }

    public static RuntimeException wrapRuntime(Throwable throwable) {
        return throwable instanceof RuntimeException?(RuntimeException)throwable:new RuntimeException(throwable);
    }

    public static Throwable unwrap(Throwable wrapped) {
        Throwable unwrapped = wrapped;

        while(true) {
            while(!(unwrapped instanceof InvocationTargetException)) {
                if(!(unwrapped instanceof UndeclaredThrowableException)) {
                    return unwrapped;
                }

                unwrapped = ((UndeclaredThrowableException)unwrapped).getUndeclaredThrowable();
            }

            unwrapped = ((InvocationTargetException)unwrapped).getTargetException();
        }
    }

    public static StackTraceElement[] getStackElements() {
        return (new Throwable()).getStackTrace();
    }

    public static String stacktraceToOneLineString(Throwable throwable) {
        return stacktraceToOneLineString(throwable, 3000);
    }

    public static String stacktraceToOneLineString(Throwable throwable, int limit) {
        HashMap replaceCharToStrMap = new HashMap();
        replaceCharToStrMap.put(Character.valueOf('\r'), " ");
        replaceCharToStrMap.put(Character.valueOf('\n'), " ");
        replaceCharToStrMap.put(Character.valueOf('\t'), " ");
        return stacktraceToString(throwable, limit, replaceCharToStrMap);
    }

    public static String stacktraceToString(Throwable throwable) {
        return stacktraceToString(throwable, 3000);
    }

    public static String stacktraceToString(Throwable throwable, int limit) {
        return stacktraceToString(throwable, limit, (Map) null);
    }

    public static String stacktraceToString(Throwable throwable, int limit, Map<Character, String> replaceCharToStrMap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        throwable.printStackTrace(new PrintStream(baos));
        String exceptionStr = baos.toString();
        int length = exceptionStr.length();
        if(limit > 0 && limit < length) {
            length = limit;
        }

        if(CollectionUtils.isNotEmpty(replaceCharToStrMap)) {
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < length; ++i) {
                char c = exceptionStr.charAt(i);
                String value = (String)replaceCharToStrMap.get(Character.valueOf(c));
                if(null != value) {
                    sb.append(value);
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        } else {
            return exceptionStr;
        }
    }
}
