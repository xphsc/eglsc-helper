package com.github.xphsc.mutable;

import com.github.xphsc.util.ClassUtil;

import java.io.Serializable;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class Booleans implements Comparable<Booleans>, Cloneable, Serializable {

    private static final long serialVersionUID = -601670961902893139L;

    public Booleans() {
    }

    public Booleans(boolean value) {
        this.value = value;
    }

    public Booleans(String value) {
        this.value = Boolean.valueOf(value).booleanValue();
    }

    public Booleans(Boolean value) {
        this.value = value.booleanValue();
    }

    public Booleans(Number number) {
        this.value = number.intValue() != 0;
    }

    // ---------------------------------------------------------------- value

    /**
     * The mutable value.
     */
    public boolean value;

    /**
     * Returns mutable value.
     */
    public boolean getValue() {
        return value;
    }

    /**
     * Sets mutable value.
     */
    public void setValue(boolean value) {
        this.value = value;
    }

    public void setValue(Boolean value) {
        this.value = value.booleanValue();
    }

    // ---------------------------------------------------------------- object

    /**
     * Stringify the value.
     */
    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    /**
     * Returns a hashcode for this value.
     */
    @Override
    public int hashCode() {
        return value ? 1231 : 1237;
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
            if (ClassUtil.isInstance(Boolean.class, (Class<?>) obj)) {
                return obj.equals(value);
            }
            if (ClassUtil.isInstance(Booleans.class, (Class<?>) obj)) {
                return value == ((Booleans) obj).value;
            }
        }
        return false;
    }

    // ---------------------------------------------------------------- compare

    /**
     * Compares value of two same instances.
     */
    public int compareTo(Booleans o) {
        return (value == o.value) ? 0 : (!value ? -1 : 1);
    }

    // ---------------------------------------------------------------- clone

    /**
     * Clones object.
     */
    @Override
    public Booleans clone() {
        return new Booleans(value);
    }
}
