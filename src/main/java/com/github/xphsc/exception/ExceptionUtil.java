package com.github.xphsc.exception;

import com.github.xphsc.io.ByteArrayOutputStream;
import com.github.xphsc.util.CollectionUtil;
import com.github.xphsc.util.StringUtil;

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

        if(CollectionUtil.isNotEmpty(replaceCharToStrMap)) {
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

    /** 将异常转换成<code>RuntimeException</code>。
     *
     * @param exception 受检异常
    * @return to <code>RuntimeException</code
    */
    public static RuntimeException toRuntimeException(Exception exception) {
        return toRuntimeException(exception, null);
    }

    /**
     * 将异常转换成<code>RuntimeException</code>。
     *
     * @param exception 受检异常
     * @param runtimeExceptionClass 转换异常的类型
     * @return to <code>RuntimeException</code
     */
    public static RuntimeException toRuntimeException(Exception exception,
                                                      Class<? extends RuntimeException> runtimeExceptionClass) {
        if (exception == null) {
            return null;
        }

        if (exception instanceof RuntimeException) {
            return (RuntimeException) exception;
        }
        if (runtimeExceptionClass == null) {
            return new RuntimeException(exception);
        }

        RuntimeException runtimeException;

        try {
            runtimeException = runtimeExceptionClass.newInstance();
        } catch (Exception ee) {
            return new RuntimeException(exception);
        }

        runtimeException.initCause(exception);
        return runtimeException;

    }

    /**
     * 抛出Throwable，但不需要声明<code>throws Throwable</code>，区分<code>Exception</code> 或</code>Error</code>
     * @param throwable 受检异常
     * @throws Exception
     */
    public static void throwExceptionOrError(Throwable throwable) throws Exception {
        if (throwable instanceof Exception) {
            throw (Exception) throwable;
        }

        if (throwable instanceof Error) {
            throw (Error) throwable;
        }

        throw new RuntimeException(throwable); // unreachable code
    }

    /**
     * 抛出Throwable，但不需要声明<code>throws Throwable</code>，区分 <code>RuntimeException</code>、<code>Exception</code>
     * 或</code>Error</code>。
     *
     * @param throwable 受检异常
     */
    public static void throwRuntimeExceptionOrError(Throwable throwable) {
        if (throwable instanceof Error) {
            throw (Error) throwable;
        }

        if (throwable instanceof RuntimeException) {
            throw (RuntimeException) throwable;
        }

        throw new RuntimeException(throwable);
    }

}
