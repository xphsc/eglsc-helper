package com.github.xphsc.bean.comparator;

/**
 * Created by ${huipei.x} on 2017-8-7.
 */
public interface Transformer<I, O> {
    O transform(I var1);
}
