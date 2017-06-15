package com.eglsc.util;

import com.sun.xml.internal.ws.util.UtilException;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.*;

/**
 * Created by ${huipei.x} on 2017-5-25.
 */
public class ArrayUtil  {

    private ArrayUtil() {
    }
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(short[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(char[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(double[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(boolean[] array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(long[] array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(int[] array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(short[] array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(char[] array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(byte[] array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(double[] array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(float[] array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(boolean[] array) {
        return array != null && array.length != 0;
    }

    public static <T> T[] newArray(Class<?> componentType, int newSize) {
        return (T[]) Array.newInstance(componentType, newSize);
    }


    public static int[] range(int excludedEnd) {
        return range(0, excludedEnd, 1);
    }

    public static int[] range(int includedStart, int excludedEnd) {
        return range(includedStart, excludedEnd, 1);
    }

    public static int[] range(int includedStart, int excludedEnd, int step) {
        int deviation;
        if(includedStart > excludedEnd) {
            deviation = includedStart;
            includedStart = excludedEnd;
            excludedEnd = deviation;
        }

        if(step <= 0) {
            step = 1;
        }

        deviation = excludedEnd - includedStart;
        int length = deviation / step;
        if(deviation % step != 0) {
            ++length;
        }

        int[] range = new int[length];

        for(int i = 0; i < length; ++i) {
            range[i] = includedStart;
            includedStart += step;
        }

        return range;
    }

    public static byte[][] split(byte[] array, int len) {
        int x = array.length / len;
        int y = array.length % len;
        byte z = 0;
        if(y != 0) {
            z = 1;
        }

        byte[][] arrays = new byte[x + z][];

        for(int i = 0; i < x + z; ++i) {
            byte[] arr = new byte[len];
            if(i == x + z - 1 && y != 0) {
                System.arraycopy(array, i * len, arr, 0, y);
            } else {
                System.arraycopy(array, i * len, arr, 0, len);
            }

            arrays[i] = arr;
        }

        return arrays;
    }


    public static <T, K> Map<T, K> zip(T[] keys, K[] values) {
        if(!isEmpty(keys) && !isEmpty(values)) {
            int size = Math.min(keys.length, values.length);
            HashMap map = new HashMap((int)((double)size / 0.75D));

            for(int i = 0; i < size; ++i) {
                map.put(keys[i], values[i]);
            }

            return map;
        } else {
            return null;
        }
    }

    public static <T> boolean contains(T[] array, T value) {
        Class componetType = array.getClass().getComponentType();
        boolean isPrimitive = false;
        if(null != componetType) {
            isPrimitive = componetType.isPrimitive();
        }

        Object[] arr$ = array;
        int len$ = array.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Object t = arr$[i$];
            if(t == value) {
                return true;
            }

            if(!isPrimitive && null != value && value.equals(t)) {
                return true;
            }
        }

        return false;
    }

    public static Integer[] wrap(int... values) {
        int length = values.length;
        Integer[] array = new Integer[length];

        for(int i = 0; i < length; ++i) {
            array[i] = Integer.valueOf(values[i]);
        }

        return array;
    }

    public static int[] unWrap(Integer... values) {
        int length = values.length;
        int[] array = new int[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i].intValue();
        }

        return array;
    }

    public static Long[] wrap(long... values) {
        int length = values.length;
        Long[] array = new Long[length];

        for(int i = 0; i < length; ++i) {
            array[i] = Long.valueOf(values[i]);
        }

        return array;
    }

    public static long[] unWrap(Long... values) {
        int length = values.length;
        long[] array = new long[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i].longValue();
        }

        return array;
    }

    public static Character[] wrap(char... values) {
        int length = values.length;
        Character[] array = new Character[length];

        for(int i = 0; i < length; ++i) {
            array[i] = Character.valueOf(values[i]);
        }

        return array;
    }

    public static char[] unWrap(Character... values) {
        int length = values.length;
        char[] array = new char[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i].charValue();
        }

        return array;
    }

    public static Byte[] wrap(byte... values) {
        int length = values.length;
        Byte[] array = new Byte[length];

        for(int i = 0; i < length; ++i) {
            array[i] = Byte.valueOf(values[i]);
        }

        return array;
    }

    public static byte[] unWrap(Byte... values) {
        int length = values.length;
        byte[] array = new byte[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i].byteValue();
        }

        return array;
    }

    public static Short[] wrap(short... values) {
        int length = values.length;
        Short[] array = new Short[length];

        for(int i = 0; i < length; ++i) {
            array[i] = Short.valueOf(values[i]);
        }

        return array;
    }

    public static short[] unWrap(Short... values) {
        int length = values.length;
        short[] array = new short[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i].shortValue();
        }

        return array;
    }

    public static Float[] wrap(float... values) {
        int length = values.length;
        Float[] array = new Float[length];

        for(int i = 0; i < length; ++i) {
            array[i] = Float.valueOf(values[i]);
        }

        return array;
    }

    public static float[] unWrap(Float... values) {
        int length = values.length;
        float[] array = new float[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i].floatValue();
        }

        return array;
    }

    public static Double[] wrap(double... values) {
        int length = values.length;
        Double[] array = new Double[length];

        for(int i = 0; i < length; ++i) {
            array[i] = Double.valueOf(values[i]);
        }

        return array;
    }

    public static double[] unWrap(Double... values) {
        int length = values.length;
        double[] array = new double[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i].doubleValue();
        }

        return array;
    }

    public static Boolean[] wrap(boolean... values) {
        int length = values.length;
        Boolean[] array = new Boolean[length];

        for(int i = 0; i < length; ++i) {
            array[i] = Boolean.valueOf(values[i]);
        }

        return array;
    }

    public static boolean[] unWrap(Boolean... values) {
        int length = values.length;
        boolean[] array = new boolean[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i].booleanValue();
        }

        return array;
    }

    public static Object[] wrap(Object obj) {
        if(isArray(obj)) {
            try {
                return (Object[])((Object[])obj);
            } catch (Exception var5) {
                String className = obj.getClass().getComponentType().getName();
                byte var4 = -1;
                switch(className.hashCode()) {
                    case -1325958191:
                        if(className.equals("double")) {
                            var4 = 7;
                        }
                        break;
                    case 104431:
                        if(className.equals("int")) {
                            var4 = 1;
                        }
                        break;
                    case 3039496:
                        if(className.equals("byte")) {
                            var4 = 4;
                        }
                        break;
                    case 3052374:
                        if(className.equals("char")) {
                            var4 = 3;
                        }
                        break;
                    case 3327612:
                        if(className.equals("long")) {
                            var4 = 0;
                        }
                        break;
                    case 64711720:
                        if(className.equals("boolean")) {
                            var4 = 5;
                        }
                        break;
                    case 97526364:
                        if(className.equals("float")) {
                            var4 = 6;
                        }
                        break;
                    case 109413500:
                        if(className.equals("short")) {
                            var4 = 2;
                        }
                }

                switch(var4) {
                    case 0:
                        return wrap((long[])((long[])obj));
                    case 1:
                        return wrap((int[])((int[])obj));
                    case 2:
                        return wrap((short[])((short[])obj));
                    case 3:
                        return wrap((char[])((char[])obj));
                    case 4:
                        return wrap((byte[])((byte[])obj));
                    case 5:
                        return wrap((boolean[])((boolean[])obj));
                    case 6:
                        return wrap((float[])((float[])obj));
                    case 7:
                        return wrap((double[])((double[])obj));
                    default:
                        throw new UtilException(var5);
                }
            }
        } else {
            throw new UtilException(StringUtil.format("[{}] is not Array!", new Object[]{obj.getClass()}));
        }
    }

    public static boolean isArray(Object obj) {
        if(null == obj) {
            throw new NullPointerException("Object check for isArray is null");
        } else {
            return obj.getClass().isArray();
        }
    }

    public static String toString(Object obj) {
        if(null == obj) {
            return null;
        } else if(isArray(obj)) {
            try {
                return Arrays.deepToString((Object[])((Object[])obj));
            } catch (Exception var5) {
                String className = obj.getClass().getComponentType().getName();
                byte var4 = -1;
                switch(className.hashCode()) {
                    case -1325958191:
                        if(className.equals("double")) {
                            var4 = 7;
                        }
                        break;
                    case 104431:
                        if(className.equals("int")) {
                            var4 = 1;
                        }
                        break;
                    case 3039496:
                        if(className.equals("byte")) {
                            var4 = 4;
                        }
                        break;
                    case 3052374:
                        if(className.equals("char")) {
                            var4 = 3;
                        }
                        break;
                    case 3327612:
                        if(className.equals("long")) {
                            var4 = 0;
                        }
                        break;
                    case 64711720:
                        if(className.equals("boolean")) {
                            var4 = 5;
                        }
                        break;
                    case 97526364:
                        if(className.equals("float")) {
                            var4 = 6;
                        }
                        break;
                    case 109413500:
                        if(className.equals("short")) {
                            var4 = 2;
                        }
                }

                switch(var4) {
                    case 0:
                        return Arrays.toString((long[])((long[])obj));
                    case 1:
                        return Arrays.toString((int[])((int[])obj));
                    case 2:
                        return Arrays.toString((short[])((short[])obj));
                    case 3:
                        return Arrays.toString((char[])((char[])obj));
                    case 4:
                        return Arrays.toString((byte[])((byte[])obj));
                    case 5:
                        return Arrays.toString((boolean[])((boolean[])obj));
                    case 6:
                        return Arrays.toString((float[])((float[])obj));
                    case 7:
                        return Arrays.toString((double[])((double[])obj));
                    default:
                        throw new UtilException(var5);
                }
            }
        } else {
            return obj.toString();
        }
    }

    public static <T> String join(T[] array, String conjunction) {
        if(null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            Object[] arr$ = array;
            int len$ = array.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Object item = arr$[i$];
                if(isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                if(isArray(item)) {
                    sb.append(join(wrap(item), conjunction));
                } else if(item instanceof Iterable) {
                    sb.append(CollectionUtils.join((Iterable)item, conjunction));
                } else if(item instanceof Iterator) {
                    sb.append(CollectionUtils.join((Iterator)item, conjunction));
                } else {
                    sb.append(item);
                }
            }

            return sb.toString();
        }
    }

    public static String join(long[] array, String conjunction) {
        if(null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            long[] arr$ = array;
            int len$ = array.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                long item = arr$[i$];
                if(isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                sb.append(item);
            }

            return sb.toString();
        }
    }

    public static String join(int[] array, String conjunction) {
        if(null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            int[] arr$ = array;
            int len$ = array.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                int item = arr$[i$];
                if(isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                sb.append(item);
            }

            return sb.toString();
        }
    }

    public static String join(short[] array, String conjunction) {
        if(null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            short[] arr$ = array;
            int len$ = array.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                short item = arr$[i$];
                if(isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                sb.append(item);
            }

            return sb.toString();
        }
    }

    public static String join(char[] array, String conjunction) {
        if(null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            char[] arr$ = array;
            int len$ = array.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                char item = arr$[i$];
                if(isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                sb.append(item);
            }

            return sb.toString();
        }
    }

    public static String join(byte[] array, String conjunction) {
        if(null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            byte[] arr$ = array;
            int len$ = array.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                byte item = arr$[i$];
                if(isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                sb.append(item);
            }

            return sb.toString();
        }
    }

    public static String join(boolean[] array, String conjunction) {
        if(null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            boolean[] arr$ = array;
            int len$ = array.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                boolean item = arr$[i$];
                if(isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                sb.append(item);
            }

            return sb.toString();
        }
    }

    public static String join(float[] array, String conjunction) {
        if(null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            float[] arr$ = array;
            int len$ = array.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                float item = arr$[i$];
                if(isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                sb.append(item);
            }

            return sb.toString();
        }
    }

    public static String join(double[] array, String conjunction) {
        if(null == array) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            double[] arr$ = array;
            int len$ = array.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                double item = arr$[i$];
                if(isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                sb.append(item);
            }

            return sb.toString();
        }
    }

    public static String join(Object array, String conjunction) {
        if(isArray(array)) {
            Class componentType = array.getClass().getComponentType();
            if(componentType.isPrimitive()) {
                String componentTypeName = componentType.getName();
                byte var5 = -1;
                switch(componentTypeName.hashCode()) {
                    case -1325958191:
                        if(componentTypeName.equals("double")) {
                            var5 = 7;
                        }
                        break;
                    case 104431:
                        if(componentTypeName.equals("int")) {
                            var5 = 1;
                        }
                        break;
                    case 3039496:
                        if(componentTypeName.equals("byte")) {
                            var5 = 4;
                        }
                        break;
                    case 3052374:
                        if(componentTypeName.equals("char")) {
                            var5 = 3;
                        }
                        break;
                    case 3327612:
                        if(componentTypeName.equals("long")) {
                            var5 = 0;
                        }
                        break;
                    case 64711720:
                        if(componentTypeName.equals("boolean")) {
                            var5 = 5;
                        }
                        break;
                    case 97526364:
                        if(componentTypeName.equals("float")) {
                            var5 = 6;
                        }
                        break;
                    case 109413500:
                        if(componentTypeName.equals("short")) {
                            var5 = 2;
                        }
                }

                switch(var5) {
                    case 0:
                        return join((long[])((long[])array), conjunction);
                    case 1:
                        return join((int[])((int[])array), conjunction);
                    case 2:
                        return join((short[])((short[])array), conjunction);
                    case 3:
                        return join((char[])((char[])array), conjunction);
                    case 4:
                        return join((byte[])((byte[])array), conjunction);
                    case 5:
                        return join((boolean[])((boolean[])array), conjunction);
                    case 6:
                        return join((float[])((float[])array), conjunction);
                    case 7:
                        return join((double[])((double[])array), conjunction);
                    default:
                        throw new UtilException("Unknown primitive type: [{}]", new Object[]{componentTypeName});
                }
            } else {
                return join((Object[])((Object[])array), conjunction);
            }
        } else {
            throw new UtilException(StringUtil.format("[{}] is not a Array!", new Object[]{array.getClass()}));
        }
    }

    public static byte[] toArray(ByteBuffer bytebuffer) {
        if(!bytebuffer.hasArray()) {
            int oldPosition = bytebuffer.position();
            bytebuffer.position(0);
            int size = bytebuffer.limit();
            byte[] buffers = new byte[size];
            bytebuffer.get(buffers);
            bytebuffer.position(oldPosition);
            return buffers;
        } else {
            return Arrays.copyOfRange(bytebuffer.array(), bytebuffer.position(), bytebuffer.limit());
        }
    }
}
