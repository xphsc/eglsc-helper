package com.github.xphsc.convert;

import com.github.xphsc.lang.Validator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ${huipei.x} on 2017-5-25.
 */
public class ConverterRegistry {

    private Map<Class<?>, Converter<?>> defaultConverterMap;
    private Map<Class<?>, Converter<?>> customConverterMap;

    public static ConverterRegistry getInstance() {
        return ConverterRegistry.SingletonHolder.instance;
    }





    public ConverterRegistry putCustom(Class<?> clazz, Converter<?> converter) {
        if(null == this.customConverterMap) {
            synchronized(this) {
                if(null == this.customConverterMap) {
                    this.customConverterMap = new ConcurrentHashMap();
                }
            }
        }

        this.customConverterMap.put(clazz, converter);
        return this;
    }

    public <T> Converter<T> getConverter(Class<T> type, boolean isCustomFirst) {
        Converter converter = null;
        if(isCustomFirst) {
            converter = this.getCustomConverter(type);
            if(null == converter) {
                converter = this.getDefaultConverter(type);
            }
        } else {
            converter = this.getDefaultConverter(type);
            if(null == converter) {
                converter = this.getCustomConverter(type);
            }
        }

        return converter;
    }

    public <T> Converter<T> getDefaultConverter(Class<T> type) {
        return null == this.defaultConverterMap?null:(Converter)this.defaultConverterMap.get(type);
    }

    public <T> Converter<T> getCustomConverter(Class<T> type) {
        return null == this.customConverterMap?null:(Converter)this.customConverterMap.get(type);
    }



    public static <T> T convert(Object toBeConvertedValue, Class<T> targetType) {
        Validator.notNull(targetType, "targetType can\'t be null!", new Object[0]);
        return null == toBeConvertedValue?null: (T) ConverterRegistry.convert(toBeConvertedValue, targetType);
    }



    private static class SingletonHolder {
        private static ConverterRegistry instance = new ConverterRegistry();

        private SingletonHolder() {
        }
    }
}
