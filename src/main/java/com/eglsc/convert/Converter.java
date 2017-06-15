package com.eglsc.convert;

/**
 * Created by ${huipei.x} on 2017-5-25.
 */
public interface Converter<T> {
    T convert(Object var1, T var2) throws IllegalArgumentException;
}