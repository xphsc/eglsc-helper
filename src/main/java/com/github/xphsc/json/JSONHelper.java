package com.github.xphsc.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * Created by ${huipei.x} on 2017-6-1.
 */
public class JSONHelper extends JSON {

    public static ValueFilter valueFilter = new JSONValueFilter();
    public static final SerializerFeature[] features = {
            SerializerFeature.WriteDateUseDateFormat,
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.WriteSlashAsSpecial,
            SerializerFeature.PrettyFormat,
    };

    public static String toJSON(Object target) {
        return JSONHelper.toJSONString(target, valueFilter, features);
    }

    public static String toJSON(Object target, String dateFormat) {
        JSONHelper.DEFFAULT_DATE_FORMAT = dateFormat;
        return JSONHelper.toJSONString(target, JSONHelper.valueFilter, JSONHelper.features);
    }

}