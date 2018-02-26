package com.github.xphsc.mutable;



/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public final class Ints extends Number implements Comparable<Ints>, Cloneable {
    private static final long serialVersionUID = -7381592836008495052L;
    private int value;

    public Ints() {
    }

    public Ints(int value) {
        this.value = value;
    }

    public Ints(Number value) {
        this(value.intValue());
    }

    public Ints(String value) throws NumberFormatException {
        this.value = Integer.parseInt(value);
    }

    public Integer get() {
        return Integer.valueOf(this.value);
    }

    public void set(int value) {
        this.value = value;
    }

    public void set(Number value) {
        this.value = value.intValue();
    }
    public static boolean isEmpty(int defaultValue) {
        return Integers.isEmpty(defaultValue);
    }
    public static boolean isNotEmpty(int defaultValue) {
        return !isEmpty(defaultValue);
    }
    public static boolean isNotEmptyAndZero(int defaultValue) {
        return isNotEmpty(defaultValue)&&defaultValue==0;
    }
    public Ints increment() {
        ++this.value;
        return this;
    }

    public Ints decrement() {
        --this.value;
        return this;
    }

    public Ints add(int operand) {
        this.value += operand;
        return this;
    }

    public Ints add(Number operand) {
        this.value += operand.intValue();
        return this;
    }

    public Ints subtract(int operand) {
        this.value -= operand;
        return this;
    }

    public Ints subtract(Number operand) {
        this.value -= operand.intValue();
        return this;
    }

    @Override
    public int intValue() {
        return this.value;
    }

    @Override
    public long longValue() {
        return (long) this.value;
    }

    @Override
    public float floatValue() {
        return (float) this.value;
    }

    @Override
    public double doubleValue() {
        return (double) this.value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Ints ? this.value == ((Ints) obj).intValue() : false;
    }

    @Override
      public int hashCode() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public int compareTo(Ints o) {
        return value < o.value ? -1 : (value == o.value ? 0 : 1);
    }
}