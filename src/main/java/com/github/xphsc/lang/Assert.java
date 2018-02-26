package com.github.xphsc.lang;

import com.github.xphsc.exception.ExceptionType;
import com.github.xphsc.util.ArrayUtil;
import com.github.xphsc.util.CollectionUtil;
import com.github.xphsc.util.StringUtil;


import java.util.Collection;
import java.util.Map;

/**
 * Created by ${huipei.x} on 2017-5-25.
 */
public class Assert {

    private Assert() {
    }

    public static void isTrue(boolean expression, String message) {
        if(!expression) {
            throw ExceptionType.ILLEGAL_ARGUMENT.newInstance(message);
        }
    }



    /** 确保表达式为真，否则抛出<code>IllegalArgumentException</code>。 */
    public static void isTrue(boolean expression, String message, Object...args) {
        isTrue(expression, null, message, args);
    }

    /** 确保表达式为真，否则抛出指定异常，默认为<code>IllegalArgumentException</code>。 */
    public static void isTrue(boolean expression, ExceptionType exceptionType, String message, Object...args) {
        if (!expression) {
            if (exceptionType == null) {
                exceptionType = ExceptionType.ILLEGAL_ARGUMENT;
            }

            throw exceptionType.newInstance(getMessage(message, args,
                    "[Assertion failed] - the expression must be true"));
        }
    }

    public static void isNull(Object object, String message) {
        if(object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNull(Object object) {
        isNull(object, "[Assertion failed] - the object argument must be null");
    }

    public static void notNull(Object object, String message) {
        if(object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

    public static void notEmpty(String text, String message) {
        if(StringUtil.isEmpty(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(String text) {
        notEmpty(text, "[Assertion failed] - this String argument must have length; it must not be null or empty");
    }

    public static void notBlank(String text, String message) {
        if(StringUtil.isBlank(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notBlank(String text) {
        notBlank(text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
    }

    public static void notContain(String textToSearch, String substring, String message) {
        if(StringUtil.isNotEmpty(textToSearch) && StringUtil.isNotEmpty(substring) && textToSearch.contains(substring)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notContain(String textToSearch, String substring) {
        notContain(textToSearch, substring, "[Assertion failed] - this String argument must not contain the substring [" + substring + "]");
    }

    public static void notEmpty(Object[] array, String message) {
        if(ArrayUtil.isEmpty(array)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Object[] array) {
        notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
    }

    public static void noNullElements(Object[] array, String message) {
        if(array != null) {
            Object[] arr$ = array;
            int len$ = array.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Object element = arr$[i$];
                if(element == null) {
                    throw new IllegalArgumentException(message);
                }
            }
        }

    }

    public static void noNullElements(Object[] array) {
        noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if(CollectionUtil.isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Collection<?> collection) {
        notEmpty(collection, "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        if(CollectionUtil.isEmpty(map)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
    }

    public static void isInstanceOf(Class<?> clazz, Object obj) {
        isInstanceOf(clazz, obj, "");
    }

    public static void isInstanceOf(Class<?> type, Object obj, String message) {
        notNull(type, "Type to check against must not be null");
        if(!type.isInstance(obj)) {
            throw new IllegalArgumentException((StringUtil.isNotEmpty(message)?message + " ":"") + "Object of class [" + (obj != null?obj.getClass().getName():"null") + "] must be an instance of " + type);
        }
    }

    public static void isAssignable(Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, "");
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
        notNull(superType, "Type to check against must not be null");
        if(subType == null || !superType.isAssignableFrom(subType)) {
            throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
        }
    }

    public static void state(boolean expression, String message) {
        if(!expression) {
            throw new IllegalStateException(message);
        }
    }

    public static void state(boolean expression) {
        state(expression, "[Assertion failed] - this state invariant must be true");
    }

    /** 取得带参数的消息。 */
    private static String getMessage(String message, Object[] args, String defaultMessage) {
        if (message == null) {
            message = defaultMessage;
        }

        if (args == null || args.length == 0) {
            return message;
        }

        return String.format(message, args);
    }

}
