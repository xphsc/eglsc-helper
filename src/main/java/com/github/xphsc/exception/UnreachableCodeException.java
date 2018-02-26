package com.github.xphsc.exception;

/**
 * Created by ${huipei.x} on 2017-6-12.
 */
public class UnreachableCodeException extends RuntimeException {

    private static final long serialVersionUID = -4015393249021653091L;

    /**
     * 构造一个空的异常.
     */
    public UnreachableCodeException() {
        super();
    }

    /**
     * 构造一个异常, 指明异常的详细信息.
     *
     * @param message 详细信息
     */
    public UnreachableCodeException(String message) {
        super(message);
    }

    /**
     * 构造一个异常, 指明引起这个异常的起因.
     *
     * @param cause 异常的起因
     */
    public UnreachableCodeException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造一个异常, 指明引起这个异常的起因.
     *
     * @param message 详细信息
     * @param cause 异常的起因
     */
    public UnreachableCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
