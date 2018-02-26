package com.github.xphsc.json;

/**
 * Created by ${huipei.x} on 2017-6-1.
 */
public class JSONArray extends com.alibaba.fastjson.JSONArray {

    public static String toJSON(Object target) {
        return JSONHelper.toJSONString(target, JSONHelper.valueFilter, JSONHelper.features);
    }

    public static String toJSON(Object target, String dateFormat) {
        JSONHelper.DEFFAULT_DATE_FORMAT = dateFormat;
        return JSONHelper.toJSONString(target, JSONHelper.valueFilter, JSONHelper.features);
    }
}
