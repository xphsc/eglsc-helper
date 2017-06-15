package com.eglsc.exception;

import com.eglsc.util.StringUtil;

/**
 * Created by ${huipei.x} on 2017-6-1.
 */
public class JSONException extends RuntimeException {

    private static final long serialVersionUID = 0L;

    public JSONException(String message) {
        super(message);
    }

    public JSONException(String message, Throwable cause) {
        super(message, cause);
    }

    public JSONException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

    public JSONException(Throwable throwable, String messageTemplate, Object... params) {
        super(StringUtil.format(messageTemplate, params), throwable);
    }
}
