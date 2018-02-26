package com.github.xphsc.util;

import com.github.xphsc.lang.Validator;
import com.github.xphsc.bean.Comparators;
import com.github.xphsc.convert.ConverterRegistry;
import com.github.xphsc.exception.UtilException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.*;

/**
 * Created by ${huipei.x} on 2017-5-25.
 */
public class ArrayUtil  {

    private ArrayUtil() {
    }

    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    /**
     * An empty immutable {@code Class} array.
     */
    public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
    /**
     * An empty immutable {@code String} array.
     */
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    /**
     * An empty immutable {@code long} array.
     */
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    /**
     * An empty immutable {@code Long} array.
     */
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
    /**
     * An empty immutable {@code int} array.
     */
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    /**
     * An empty immutable {@code Integer} array.
     */
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
    /**
     * An empty immutable {@code short} array.
     */
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    /**
     * An empty immutable {@code Short} array.
     */
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
    /**
     * An empty immutable {@code byte} array.
     */
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    /**
     * An empty immutable {@code Byte} array.
     */
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
    /**
     * An empty immutable {@code double} array.
     */
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    /**
     * An empty immutable {@code Double} array.
     */
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
    /**
     * An empty immutable {@code float} array.
     */
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    /**
     * An empty immutable {@code Float} array.
     */
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
    /**
     * An empty immutable {@code boolean} array.
     */
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    /**
     * An empty immutable {@code Boolean} array.
     */
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
    /**
     * An empty immutable {@code char} array.
     */
    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
    /**
     * An empty immutable {@code Character} array.
     */
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];
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

    public static <T> T[] toArray(T... arrays) {
        return ArrayUtil.toArray(arrays);
    }

    public static <T> T[] toArray(Collection<T> collection, Class<T> arrayComponentType) {
        if(null == collection) {
            return null;
        } else {
            Validator.notNull(arrayComponentType, "arrayComponentType must not be null", new Object[0]);
            Object[] array = ArrayUtil.newArray(arrayComponentType, collection.size());
            return (T[]) collection.toArray(array);
        }
    }

    public static <T> T[] toArray(String[] toBeConvertedValue, Class<T> targetType) {
        return null == toBeConvertedValue?null: (T[]) (Object[]) ((Object[]) ConverterRegistry.convert(toBeConvertedValue, targetType));
    }

    public static <T> T[] sortArray(T[] arrays) {
        if(null == arrays) {
            return (T[]) ArrayUtil.toArray(new Object[0]);
        } else {
            Arrays.sort(arrays);
            return arrays;
        }
    }

    @SafeVarargs
    public static <T> T[] sortArray(T[] arrays, Comparator... comparators) {
        if(null == arrays) {
            return (T[]) ArrayUtil.toArray(new Object[0]);
        } else if(Validator.isNullOrEmpty(comparators)) {
            return arrays;
        } else {
            Arrays.sort(arrays, toComparator(comparators));
            return arrays;
        }
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
                    sb.append(CollectionUtil.join((Iterable) item, conjunction));
                } else if(item instanceof Iterator) {
                    sb.append(CollectionUtil.join((Iterator) item, conjunction));
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

    public static void reverse(final Object[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>Reverses the order of the given array.</p>
     *
     * <p>This method does nothing for a {@code null} input array.</p>
     *
     * @param array  the array to reverse, may be {@code null}
     */
    public static void reverse(final long[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>Reverses the order of the given array.</p>
     *
     * <p>This method does nothing for a {@code null} input array.</p>
     *
     * @param array  the array to reverse, may be {@code null}
     */
    public static void reverse(final int[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>Reverses the order of the given array.</p>
     *
     * <p>This method does nothing for a {@code null} input array.</p>
     *
     * @param array  the array to reverse, may be {@code null}
     */
    public static void reverse(final short[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>Reverses the order of the given array.</p>
     *
     * <p>This method does nothing for a {@code null} input array.</p>
     *
     * @param array  the array to reverse, may be {@code null}
     */
    public static void reverse(final char[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>Reverses the order of the given array.</p>
     *
     * <p>This method does nothing for a {@code null} input array.</p>
     *
     * @param array  the array to reverse, may be {@code null}
     */
    public static void reverse(final byte[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>Reverses the order of the given array.</p>
     *
     * <p>This method does nothing for a {@code null} input array.</p>
     *
     * @param array  the array to reverse, may be {@code null}
     */
    public static void reverse(final double[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>Reverses the order of the given array.</p>
     *
     * <p>This method does nothing for a {@code null} input array.</p>
     *
     * @param array  the array to reverse, may be {@code null}
     */
    public static void reverse(final float[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>Reverses the order of the given array.</p>
     *
     * <p>This method does nothing for a {@code null} input array.</p>
     *
     * @param array  the array to reverse, may be {@code null}
     */
    public static void reverse(final boolean[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     * </p>
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     * </p>
     *
     * @param array
     *            the array to reverse, may be {@code null}
     * @param startIndexInclusive
     *            the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *            change.
     * @param endIndexExclusive
     *            elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *            change. Overvalue (&gt;array.length) is demoted to array length.
     * @since 3.2
     */
    public static void reverse(final boolean[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = startIndexInclusive < 0 ? 0 : startIndexInclusive;
        int j = Math.min(array.length, endIndexExclusive) - 1;
        boolean tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     * </p>
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     * </p>
     *
     * @param array
     *            the array to reverse, may be {@code null}
     * @param startIndexInclusive
     *            the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *            change.
     * @param endIndexExclusive
     *            elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *            change. Overvalue (&gt;array.length) is demoted to array length.
     * @since 3.2
     */
    public static void reverse(final byte[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = startIndexInclusive < 0 ? 0 : startIndexInclusive;
        int j = Math.min(array.length, endIndexExclusive) - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     * </p>
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     * </p>
     *
     * @param array
     *            the array to reverse, may be {@code null}
     * @param startIndexInclusive
     *            the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *            change.
     * @param endIndexExclusive
     *            elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *            change. Overvalue (&gt;array.length) is demoted to array length.
     * @since 3.2
     */
    public static void reverse(final char[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = startIndexInclusive < 0 ? 0 : startIndexInclusive;
        int j = Math.min(array.length, endIndexExclusive) - 1;
        char tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     * </p>
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     * </p>
     *
     * @param array
     *            the array to reverse, may be {@code null}
     * @param startIndexInclusive
     *            the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *            change.
     * @param endIndexExclusive
     *            elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *            change. Overvalue (&gt;array.length) is demoted to array length.
     * @since 3.2
     */
    public static void reverse(final double[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = startIndexInclusive < 0 ? 0 : startIndexInclusive;
        int j = Math.min(array.length, endIndexExclusive) - 1;
        double tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     * </p>
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     * </p>
     *
     * @param array
     *            the array to reverse, may be {@code null}
     * @param startIndexInclusive
     *            the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *            change.
     * @param endIndexExclusive
     *            elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *            change. Overvalue (&gt;array.length) is demoted to array length.
     * @since 3.2
     */
    public static void reverse(final float[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = startIndexInclusive < 0 ? 0 : startIndexInclusive;
        int j = Math.min(array.length, endIndexExclusive) - 1;
        float tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     * </p>
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     * </p>
     *
     * @param array
     *            the array to reverse, may be {@code null}
     * @param startIndexInclusive
     *            the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *            change.
     * @param endIndexExclusive
     *            elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *            change. Overvalue (&gt;array.length) is demoted to array length.
     * @since 3.2
     */
    public static void reverse(final int[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = startIndexInclusive < 0 ? 0 : startIndexInclusive;
        int j = Math.min(array.length, endIndexExclusive) - 1;
        int tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     * </p>
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     * </p>
     *
     * @param array
     *            the array to reverse, may be {@code null}
     * @param startIndexInclusive
     *            the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *            change.
     * @param endIndexExclusive
     *            elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *            change. Overvalue (&gt;array.length) is demoted to array length.
     * @since 3.2
     */
    public static void reverse(final long[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = startIndexInclusive < 0 ? 0 : startIndexInclusive;
        int j = Math.min(array.length, endIndexExclusive) - 1;
        long tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     * </p>
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     * </p>
     *
     * @param array
     *            the array to reverse, may be {@code null}
     * @param startIndexInclusive
     *            the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *            change.
     * @param endIndexExclusive
     *            elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *            change. Overvalue (&gt;array.length) is demoted to array length.
     * @since 3.2
     */
    public static void reverse(final Object[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = startIndexInclusive < 0 ? 0 : startIndexInclusive;
        int j = Math.min(array.length, endIndexExclusive) - 1;
        Object tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     * </p>
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     * </p>
     *
     * @param array
     *            the array to reverse, may be {@code null}
     * @param startIndexInclusive
     *            the starting index. Undervalue (&lt;0) is promoted to 0, overvalue (&gt;array.length) results in no
     *            change.
     * @param endIndexExclusive
     *            elements up to endIndex-1 are reversed in the array. Undervalue (&lt; start index) results in no
     *            change. Overvalue (&gt;array.length) is demoted to array length.
     * @since 3.2
     */
    public static void reverse(final short[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = startIndexInclusive < 0 ? 0 : startIndexInclusive;
        int j = Math.min(array.length, endIndexExclusive) - 1;
        short tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    public static char[] clone(final char[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }
    public static byte[] clone(final byte[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.</p>
     *
     * <p>This method returns {@code null} for a {@code null} input array.</p>
     *
     * @param array  the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static double[] clone(final double[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.</p>
     *
     * <p>This method returns {@code null} for a {@code null} input array.</p>
     *
     * @param array  the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static float[] clone(final float[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.</p>
     *
     * <p>This method returns {@code null} for a {@code null} input array.</p>
     *
     * @param array  the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static boolean[] clone(final boolean[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    public static <T> T[] clone(final T[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }
    public static short[] clone(final short[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    public static int[] clone(final int[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    public static long[] clone(final long[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
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

    public static <T> T[] addAll(final T[] array1, final T... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final Class<?> type1 = array1.getClass().getComponentType();
        @SuppressWarnings("unchecked") // OK, because array is of type T
        final
        T[] joinedArray = (T[]) Array.newInstance(type1, array1.length + array2.length);
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        try {
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        } catch (final ArrayStoreException ase) {
            // Check if problem was due to incompatible types
            /*
             * We do this here, rather than before the copy because:
             * - it would be a wasted check most of the time
             * - safer, in case check turns out to be too strict
             */
            final Class<?> type2 = array2.getClass().getComponentType();
            if (!type1.isAssignableFrom(type2)){
                throw new IllegalArgumentException("Cannot store "+type2.getName()+" in an array of "
                        +type1.getName(), ase);
            }
            throw ase; // No, so rethrow original
        }
        return joinedArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.</p>
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.</p>
     *
     * <pre>
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * </pre>
     *
     * @param array1  the first array whose elements are added to the new array.
     * @param array2  the second array whose elements are added to the new array.
     * @return The new boolean[] array.
     * @since 2.1
     */
    public static boolean[] addAll(final boolean[] array1, final boolean... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final boolean[] joinedArray = new boolean[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.</p>
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.</p>
     *
     * <pre>
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * </pre>
     *
     * @param array1  the first array whose elements are added to the new array.
     * @param array2  the second array whose elements are added to the new array.
     * @return The new char[] array.
     * @since 2.1
     */
    public static char[] addAll(final char[] array1, final char... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final char[] joinedArray = new char[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.</p>
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.</p>
     *
     * <pre>
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * </pre>
     *
     * @param array1  the first array whose elements are added to the new array.
     * @param array2  the second array whose elements are added to the new array.
     * @return The new byte[] array.
     * @since 2.1
     */
    public static byte[] addAll(final byte[] array1, final byte... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final byte[] joinedArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.</p>
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.</p>
     *
     * <pre>
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * </pre>
     *
     * @param array1  the first array whose elements are added to the new array.
     * @param array2  the second array whose elements are added to the new array.
     * @return The new short[] array.
     * @since 2.1
     */
    public static short[] addAll(final short[] array1, final short... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final short[] joinedArray = new short[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.</p>
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.</p>
     *
     * <pre>
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * </pre>
     *
     * @param array1  the first array whose elements are added to the new array.
     * @param array2  the second array whose elements are added to the new array.
     * @return The new int[] array.
     * @since 2.1
     */
    public static int[] addAll(final int[] array1, final int... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final int[] joinedArray = new int[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.</p>
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.</p>
     *
     * <pre>
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * </pre>
     *
     * @param array1  the first array whose elements are added to the new array.
     * @param array2  the second array whose elements are added to the new array.
     * @return The new long[] array.
     * @since 2.1
     */
    public static long[] addAll(final long[] array1, final long... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final long[] joinedArray = new long[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.</p>
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.</p>
     *
     * <pre>
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * </pre>
     *
     * @param array1  the first array whose elements are added to the new array.
     * @param array2  the second array whose elements are added to the new array.
     * @return The new float[] array.
     * @since 2.1
     */
    public static float[] addAll(final float[] array1, final float... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final float[] joinedArray = new float[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.</p>
     * <p>The new array contains all of the element of {@code array1} followed
     * by all of the elements {@code array2}. When an array is returned, it is always
     * a new array.</p>
     *
     * <pre>
     * ArrayUtils.addAll(array1, null)   = cloned copy of array1
     * ArrayUtils.addAll(null, array2)   = cloned copy of array2
     * ArrayUtils.addAll([], [])         = []
     * </pre>
     *
     * @param array1  the first array whose elements are added to the new array.
     * @param array2  the second array whose elements are added to the new array.
     * @return The new double[] array.
     * @since 2.1
     */
    public static double[] addAll(final double[] array1, final double... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        final double[] joinedArray = new double[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }
    public static <T> T[] add(final T[] array, final T element) {
        Class<?> type;
        if (array != null){
            type = array.getClass().getComponentType();
        } else if (element != null) {
            type = element.getClass();
        } else {
            throw new IllegalArgumentException("Arguments cannot both be null");
        }
        @SuppressWarnings("unchecked") // type must be T
        final
        T[] newArray = (T[]) copyArrayGrow1(array, type);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.</p>
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.</p>
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     *  whose component type is the same as the element.</p>
     *
     * <pre>
     * ArrayUtils.add(null, true)          = [true]
     * ArrayUtils.add([true], false)       = [true, false]
     * ArrayUtils.add([true, false], true) = [true, false, true]
     * </pre>
     *
     * @param array  the array to copy and add the element to, may be {@code null}
     * @param element  the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     * @since 2.1
     */
    public static boolean[] add(final boolean[] array, final boolean element) {
        final boolean[] newArray = (boolean[])copyArrayGrow1(array, Boolean.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.</p>
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.</p>
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     *  whose component type is the same as the element.</p>
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array  the array to copy and add the element to, may be {@code null}
     * @param element  the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     * @since 2.1
     */
    public static byte[] add(final byte[] array, final byte element) {
        final byte[] newArray = (byte[])copyArrayGrow1(array, Byte.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.</p>
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.</p>
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     *  whose component type is the same as the element.</p>
     *
     * <pre>
     * ArrayUtils.add(null, '0')       = ['0']
     * ArrayUtils.add(['1'], '0')      = ['1', '0']
     * ArrayUtils.add(['1', '0'], '1') = ['1', '0', '1']
     * </pre>
     *
     * @param array  the array to copy and add the element to, may be {@code null}
     * @param element  the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     * @since 2.1
     */
    public static char[] add(final char[] array, final char element) {
        final char[] newArray = (char[])copyArrayGrow1(array, Character.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.</p>
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.</p>
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     *  whose component type is the same as the element.</p>
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array  the array to copy and add the element to, may be {@code null}
     * @param element  the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     * @since 2.1
     */
    public static double[] add(final double[] array, final double element) {
        final double[] newArray = (double[])copyArrayGrow1(array, Double.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.</p>
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.</p>
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     *  whose component type is the same as the element.</p>
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array  the array to copy and add the element to, may be {@code null}
     * @param element  the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     * @since 2.1
     */
    public static float[] add(final float[] array, final float element) {
        final float[] newArray = (float[])copyArrayGrow1(array, Float.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.</p>
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.</p>
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     *  whose component type is the same as the element.</p>
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array  the array to copy and add the element to, may be {@code null}
     * @param element  the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     * @since 2.1
     */
    public static int[] add(final int[] array, final int element) {
        final int[] newArray = (int[])copyArrayGrow1(array, Integer.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.</p>
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.</p>
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     *  whose component type is the same as the element.</p>
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array  the array to copy and add the element to, may be {@code null}
     * @param element  the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     * @since 2.1
     */
    public static long[] add(final long[] array, final long element) {
        final long[] newArray = (long[])copyArrayGrow1(array, Long.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.</p>
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.</p>
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     *  whose component type is the same as the element.</p>
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array  the array to copy and add the element to, may be {@code null}
     * @param element  the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     * @since 2.1
     */
    public static short[] add(final short[] array, final short element) {
        final short[] newArray = (short[])copyArrayGrow1(array, Short.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    private static Object copyArrayGrow1(final Object array, final Class<?> newArrayComponentType) {
        if (array != null) {
            final int arrayLength = Array.getLength(array);
            final Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
            System.arraycopy(array, 0, newArray, 0, arrayLength);
            return newArray;
        }
        return Array.newInstance(newArrayComponentType, 1);
    }
    private static <O> Comparator<O> toComparator(Comparator... comparators) {
        return 1 == comparators.length?comparators[0]: Comparators.chainedComparator(comparators);
    }

}
