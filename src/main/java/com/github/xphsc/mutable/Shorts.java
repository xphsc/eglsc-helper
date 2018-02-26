package com.github.xphsc.mutable;

import com.github.xphsc.util.ClassUtil;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class Shorts extends Number implements Comparable<Shorts>, Cloneable {

    private static final long serialVersionUID = -6971948552591529691L;

    public Shorts() {
    }

    public Shorts(short value) {
        this.value = value;
    }

    public Shorts(String value) {
        this.value = Short.parseShort(value);
    }

    public Shorts(Number number) {
        this.value = number.shortValue();
    }

    // ---------------------------------------------------------------- value

    /**
     * The mutable value.
     */
    public short value;

    /**
     * Returns mutable value.
     */
    public short getValue() {
        return value;
    }

    /**
     * Sets mutable value.
     */
    public void setValue(short value) {
        this.value = value;
    }

    /**
     * Sets mutable value from a Number.
     */
    public void setValue(Number value) {
        this.value = value.shortValue();
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
            if (ClassUtil.isInstance(Short.class, (Class<?>) obj)) {
                return obj.equals(value);
            }
            if (ClassUtil.isInstance(Shorts.class, (Class<?>) obj)) {
                return value == ((Shorts) obj).value;
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
    public int compareTo(Shorts other) {
        return value < other.value ? -1 : (value == other.value ? 0 : 1);
    }

    // ---------------------------------------------------------------- clone

    /**
     * Clones object.
     */
    @Override
    public Shorts clone() {
        return new Shorts(value);
    }

}
