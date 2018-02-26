package com.github.xphsc.bean;

import com.github.xphsc.bean.register.RegisterBean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ${huipei.x} on 2017/6/13
 */
public class BeanByRefMapper {




    public static <T> T copyByRefMapper(Object orig, Class<T> targetClass) {
       return RegisterBean.getBeanOfMapper(orig,targetClass);
    }



    public static <T> List<T> copyByRefListMapper(List orig, Class<T> targetClass) {
        List<T> list = new ArrayList<>(orig.size());
        for (Object object : orig) {
            list.add(copyByRefMapper(object, targetClass));
        }
        return list;
    }




}
