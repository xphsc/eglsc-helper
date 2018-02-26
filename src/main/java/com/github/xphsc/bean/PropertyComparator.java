package com.github.xphsc.bean;


import com.github.xphsc.lang.Validator;
import com.github.xphsc.convert.ConverterRegistry;
import com.github.xphsc.util.ClassUtil;
import com.github.xphsc.util.ObjectUtil;
import com.github.xphsc.util.PropertyUtil;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by ${huipei.x} on 2017/6/13
 */
public class PropertyComparator <T> implements Closure<T>, Comparator<T>, Serializable  {

    private final String propertyName;
    private Comparator comparator;
    private Object propertyValue;
    private Class<? extends Comparable> propertyValueConvertToClass;

    public PropertyComparator(String propertyName) {
        Validator.notBlank(propertyName, "propertyName can\'t be blank!", new Object[0]);
        this.propertyName = propertyName;
    }

    public PropertyComparator(String propertyName, Comparator comparator) {
        Validator.notBlank(propertyName, "propertyName can\'t be blank!", new Object[0]);
        this.propertyName = propertyName;
        this.comparator = comparator;
    }

    public PropertyComparator(String propertyName, Class<? extends Comparable> propertyValueConvertToClass) {
        Validator.notBlank(propertyName, "propertyName can\'t be blank!", new Object[0]);
        this.propertyName = propertyName;
        this.propertyValueConvertToClass = propertyValueConvertToClass;
    }

    public PropertyComparator(String propertyName, Object propertyValue) {
        Validator.notBlank(propertyName, "propertyName can\'t be blank!", new Object[0]);
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    public void execute(T input) {
        if(null != input) {
            PropertyUtil.setProperty(input, this.propertyName, this.propertyValue);
        }
    }

    @Override
    public int compare(T o1, T o2) {
        if(o1 == o2) {
            return 0;
        } else if(null == o1) {
            return 1;
        } else if(null == o2) {
            return -1;
        } else {
            Comparable propertyValue1 = (Comparable) PropertyUtil.getProperty(o1, this.propertyName);
            Comparable propertyValue2 = (Comparable)PropertyUtil.getProperty(o2, this.propertyName);
            if(null != this.propertyValueConvertToClass) {
                propertyValue1 = (Comparable) ConverterRegistry.convert(propertyValue1, this.propertyValueConvertToClass);
                propertyValue2 = (Comparable) ConverterRegistry.convert(propertyValue2, this.propertyValueConvertToClass);
            }

            return null != this.comparator?this.comparator.compare(propertyValue1, propertyValue2):this.compare(o1, o2, propertyValue1, propertyValue2);
        }
    }

    private int compare(T o1, T o2, Comparable propertyValue1, Comparable propertyValue2) {
        boolean nullPropertyValueGreater = false;
        int compareTo = ObjectUtil.compare(propertyValue1, propertyValue2, nullPropertyValueGreater);
        if(0 != compareTo) {
            return compareTo;
        } else {
            return this.compareWithSameValue(o1, o2);
        }

    }

    private int compareWithSameValue(T t1, T t2) {
        return ClassUtil.isInstance(t1, Comparable.class)?((Comparable)t1).compareTo(t2):1;
    }


}
