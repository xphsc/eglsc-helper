package com.github.xphsc.mutable;

import com.github.xphsc.util.ClassUtil;
import org.apache.commons.beanutils.converters.IntegerConverter;


/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public final class Integers  extends Number implements Comparable<Integers>, Cloneable {

    private static final long serialVersionUID = 2950981847144907872L;

    public Integers() {
    }

    public Integers(int value) {
        this.value = value;
    }

    public Integers(String value) {
        this.value = Integer.parseInt(value);
    }

    public Integers(Number number) {
        this.value = number.intValue();
    }

    public static Integer toInteger(Object toBeConvertedValue) {
        return toInteger(toBeConvertedValue, (Integer)null);
    }
    public void set(int value) {
        this.value = value;
    }

    public void set(Number value) {
        this.value = value.intValue();
    }
    public static Integer toInteger(Object toBeConvertedValue, Integer defaultValue) {
        return (Integer)(new IntegerConverter(defaultValue)).convert(Integer.class, toBeConvertedValue);
    }

    public static boolean isEmpty(Integer defaultValue) {
       return defaultValue==null;
    }
    public static boolean isNotEmpty(Integer defaultValue) {
        return !isEmpty(defaultValue);
    }

    public void increment() {
        value++;
    }

    /**
     * Decrements the value.
     *
     * @since Commons Lang 2.2
     */
    public void decrement() {
        value--;
    }
    // ---------------------------------------------------------------- value

    /**
     * The mutable value.
     */
    public int value;

    /**
     * Returns mutable value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets mutable value.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Sets mutable value from a Number.
     */
    public void setValue(Number value) {
        this.value = value.intValue();
    }

    // ---------------------------------------------------------------- object

    /**
     * Stringify the value.
     */
    @Override
    public String toString() {
        return Integer.toString(value);
    }

    /**
     * Returns a hashcode for this value.
     */
    @Override
    public int hashCode() {
        return value;
    }

    /**
     * Compares this object to the specified object.
     *
     * @param obj the object to compare with.
     * @return <code>true</code> if the objects are the same; <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (ClassUtil.isInstance(Integer.class, (Class<?>) obj)) {
                return obj.equals(value);
            }
            if (ClassUtil.isInstance(Integers.class, (Class<?>) obj)) {
                return value == ((Integers) obj).value;
            }
        }
        return false;
    }

    // ---------------------------------------------------------------- number

    /**
     * Returns the value as a int.
     */
    @Override
    public int intValue() {
        return value;
    }

    /**
     * Returns the value as a long.
     */
    @Override
    public long longValue() {
        return value;
    }

    /**
     * Returns the value as a float.
     */
    @Override
    public float floatValue() {
        return value;
    }

    /**
     * Returns the value as a double.
     */
    @Override
    public double doubleValue() {
        return value;
    }

    // ---------------------------------------------------------------- compare

    /**
     * Compares value of two same instances.
     */
    @Override
    public int compareTo(Integers other) {
        return value < other.value ? -1 : (value == other.value ? 0 : 1);
    }

    // ---------------------------------------------------------------- clone

    /**
     * Clones object.
     */
    @Override
    public Integers clone() {
        return new Integers(value);
    }
}
