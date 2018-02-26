package com.github.xphsc.mutable;

import com.github.xphsc.util.ClassUtil;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class Floats extends Number implements Comparable<Floats>, Cloneable {

    private static final long serialVersionUID = 1115554883981652299L;

    public Floats() {
    }

    public Floats(float value) {
        this.value = value;
    }

    public Floats(String value) {
        this.value = Float.parseFloat(value);
    }

    public Floats(Number number) {
        this.value = number.floatValue();
    }

    // ---------------------------------------------------------------- value

    /**
     * The mutable value.
     */
    public float value;

    /**
     * Returns mutable value.
     */
    public float getValue() {
        return value;
    }

    /**
     * Sets mutable value.
     */
    public void setValue(float value) {
        this.value = value;
    }

    /**
     * Sets mutable value from a Number.
     */
    public void setValue(Number value) {
        this.value = value.floatValue();
    }

    // ---------------------------------------------------------------- object

    /**
     * Stringify the value.
     */
    @Override
    public String toString() {
        return Float.toString(value);
    }

    /**
     * Returns a hashcode for this value.
     */
    @Override
    public int hashCode() {
        return Float.floatToIntBits(value);
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
            if (ClassUtil.isInstance(Float.class, (Class<?>) obj)) {
                return obj.equals(value);
            }
            if (ClassUtil.isInstance(Floats.class, (Class<?>) obj)) {
                return Float.floatToIntBits(value) == Float.floatToIntBits(((Floats) obj).value);
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
        return (long) value;
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
     * Checks whether the value is the special NaN value.
     */
    public boolean isNaN() {
        return Float.isNaN(value);
    }

    /**
     * Checks whether the float value is infinite.
     */
    public boolean isInfinite() {
        return Float.isInfinite(value);
    }

    /**
     * Compares value of two same instances.
     */
    public int compareTo(Floats other) {
        return Float.compare(value, other.value);
    }

    // ---------------------------------------------------------------- clone

    /**
     * Clones object.
     */
    @Override
    public Floats clone() {
        return new Floats(value);
    }
}
