package com.github.xphsc.mutable;

import com.github.xphsc.util.ClassUtil;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class Doubles extends Number implements Comparable<Doubles>, Cloneable {

    private static final long serialVersionUID = -4925002798148567494L;

    public Doubles() {
    }

    public Doubles(double value) {
        this.value = value;
    }

    public Doubles(String value) {
        this.value = Double.parseDouble(value);
    }

    public Doubles(Number number) {
        this.value = number.doubleValue();
    }


    // ---------------------------------------------------------------- value

    /**
     * The mutable value.
     */
    public double value;

    /**
     * Returns mutable value.
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets mutable value.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Sets mutable value from a Number.
     */
    public void setValue(Number value) {
        this.value = value.doubleValue();
    }

    // ---------------------------------------------------------------- object

    /**
     * Stringify the value.
     */
    @Override
    public String toString() {
        return Double.toString(value);
    }

    /**
     * Returns a hashcode for this value.
     */
    @Override
    public int hashCode() {
        long bits = Double.doubleToLongBits(value);
        return (int) (bits ^ (bits >>> 32));
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
            if (ClassUtil.isInstance(Double.class, (Class<?>) obj)) {
                return obj.equals(value);
            }
            if (ClassUtil.isInstance(Doubles.class, (Class<?>) obj)) {
                return Double.doubleToLongBits(value) == Double.doubleToLongBits(((Doubles) obj).value);
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
     * Returns the value as a float..
     */
    @Override
    public float floatValue() {
        return (float) value;
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
        return Double.isNaN(value);
    }

    /**
     * Checks whether the double value is infinite.
     */
    public boolean isInfinite() {
        return Double.isInfinite(value);
    }

    /**
     * Compares value of two same instances.
     */
    public int compareTo(Doubles other) {
        return Double.compare(value, other.value);
    }

    // ---------------------------------------------------------------- clone

    /**
     * Clones object.
     */
    @Override
    public Doubles clone() {
        return new Doubles(value);
    }

}
