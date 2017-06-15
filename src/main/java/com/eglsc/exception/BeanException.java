package com.eglsc.exception;

/**
 * Created by ${huipei.x} on 2017-6-12.
 */
public class BeanException extends RuntimeException{

    private static final long serialVersionUID = -1699987643831455524L;

    public BeanException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanException(Throwable cause) {
        super(cause);
    }
}
