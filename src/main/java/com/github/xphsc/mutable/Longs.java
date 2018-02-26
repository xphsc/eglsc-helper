package com.github.xphsc.mutable;

import com.github.xphsc.util.ClassUtil;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class Longs extends Number implements Comparable<Longs>, Cloneable {

    private static final long serialVersionUID = 5118478602649863710L;

    public Longs() {
    }

    public Longs(long value) {
        this.value = value;
    }

    public Longs(String value) {
        this.value = Long.parseLong(value);
    }

    public Longs(Number number) {
        this.value = number.longValue();
    }

    // ---------------------------------------------------------------- value

    /**
     * The mutable value.
     */
    public long value;

    /**
     * Returns mutable value.
     */
    public long getValue() {
        return value;
    }

    /**
     * Sets mutable value.
     */
    public void setValue(long value) {
        this.value = value;
    }

    /**
     * Sets mutable value from a Number.
     */
    public void setValue(Number value) {
        this.value = value.longValue();
    }

    // ---------------------------------------------------------------- object

    /**
     * Stringify the value.
     */
    @Override
    public String toString() {
        return Long.toString(value);
    }

    /**
     * Returns a hashcode for this value.
     */
    @Override
    public int hashCode() {
        return (int) (value ^ (value >>> 32));
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
            if (ClassUtil.isInstance(Long.class, (Class<?>) obj)) {
                return obj.equals(value);
            }
            if (ClassUtil.isInstance(Longs.class, (Class<?>) obj)) {
                return value == ((Longs) obj).value;
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
        return (int) value;
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
    public int compareTo(Longs other) {
        return value < other.value ? -1 : (value == other.value ? 0 : 1);
    }

    // ---------------------------------------------------------------- clone

    /**
     * Clones object.
     */
    @Override
    public Longs clone() {
        return new Longs(value);
    }

}
