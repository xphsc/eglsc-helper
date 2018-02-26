package com.github.xphsc.mutable;

import com.github.xphsc.util.ClassUtil;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class Bytes  extends Number implements Comparable<Bytes>, Cloneable {

    private static final long serialVersionUID = 1552743534400429514L;

    public Bytes() {

    }

    public Bytes(byte value) {
        this.value = value;
    }

    public Bytes(String value) {
        this.value = Byte.parseByte(value);
    }

    public Bytes(Number number) {
        this.value = number.byteValue();
    }

    // ---------------------------------------------------------------- value

    /**
     * The mutable value.
     */
    public byte value;

    /**
     * Returns mutable value.
     */
    public byte getValue() {
        return value;
    }

    /**
     * Sets mutable value.
     */
    public void setValue(byte value) {
        this.value = value;
    }

    /**
     * Sets mutable value from a Number.
     */
    public void setValue(Number value) {
        this.value = value.byteValue();
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
            if (ClassUtil.isInstance(Byte.class, (Class<?>) obj)) {
                return obj.equals(value);
            }
            if (obj instanceof Bytes) {
                return value == ((Bytes) obj).value;
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
    public int compareTo(Bytes other) {
        return value < other.value ? -1 : (value == other.value ? 0 : 1);
    }

    // ---------------------------------------------------------------- clone

    /**
     * Clones object.
     */
    @Override
    public Bytes clone() {
        return new Bytes(value);
    }

}
