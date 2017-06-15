package com.eglsc.convert;

import com.eglsc.util.ClassUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;
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







    private static class SingletonHolder {
        private static ConverterRegistry instance = new ConverterRegistry();

        private SingletonHolder() {
        }
    }
}
