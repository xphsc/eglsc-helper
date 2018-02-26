package com.github.xphsc.mutable;

import java.io.Serializable;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class Objects implements Comparable<Objects>, Cloneable, Serializable {

    /**
     * Required for serialization support.
     *
     * @see java.io.Serializable
     */
    private static final long serialVersionUID = 86241875189L;

    /** The mutable value. */
    private Object value;

    /**
     * Constructs a new MutableObject with the default value of <code>null</code>.
     */
    public Objects() {
        super();
    }

    /**
     * Constructs a new MutableObject with the specified value.
     *
     * @param value  the initial value to store
     */
    public Objects(Object value) {
        super();
        this.value = value;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the value.
     *
     * @return the value, may be null
     */
    public Object getValue() {
        return this.value;
    }

    /**
     * Sets the value.
     *
     * @param value  the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    //-----------------------------------------------------------------------
    /**
     * Compares this object against the specified object. The result is <code>true</code> if and only if the argument
     * is not <code>null</code> and is a <code>MutableObject</code> object that contains the same <code>Object</code>
     * value as this object.
     *
     * @param obj  the object to compare with, null returns false
     * @return <code>true</code> if the objects are the same; <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Objects) {
            Object other = ((Objects) obj).value;
            return value == other || (value != null && value.equals(other));
        }
        return false;
    }

    /**
     * Returns the value's hash code or <code>0</code> if the value is <code>null</code>.
     *
     * @return the value's hash code or <code>0</code> if the value is <code>null</code>.
     */
    @Override
    public int hashCode() {
        return value == null ? 0 : value.hashCode();
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the String value of this mutable.
     *
     * @return the mutable value as a string
     */
    @Override
    public String toString() {
        return value == null ? "null" : value.toString();
    }


    @Override
    public int compareTo(Objects other) {
        return  value == other.value ? 0 : 1;
    }
}
