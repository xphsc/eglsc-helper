package com.github.xphsc.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ${huipei.x} on 2017/6/20
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {


    String title();


    int order() default 9999;
}
