package com.eglsc.json;

import com.eglsc.exception.JSONException;

import java.io.Writer;

/**
 * Created by ${huipei.x} on 2017-6-1.
 */
public interface JSON {
    Writer write(Writer var1) throws JSONException;

    Writer write(Writer var1, int var2, int var3) throws JSONException;

    String toJSONString(int var1) throws JSONException;
}
