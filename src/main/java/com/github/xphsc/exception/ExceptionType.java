package com.github.xphsc.exception;

/** 错误类型
 * Created by ${huipei.x} on 2017-6-12.
 *
 */
public  enum ExceptionType {

    ILLEGAL_ARGUMENT {
        @Override
        public RuntimeException newInstance(String message) {
            return new IllegalArgumentException(message);
        }
    },

    ILLEGAL_STATE {
        @Override
        public RuntimeException newInstance(String message) {
            return new IllegalStateException(message);
        }
    },

    NULL_POINT {
        @Override
        public RuntimeException newInstance(String message) {
            return new NullPointerException(message);
        }
    },

    UNREACHABLE_CODE {
        @Override
        public RuntimeException newInstance(String message) {
            return new UnreachableCodeException(message);
        }
    },

    UNEXPECTED_FAILURE {
        @Override
        public RuntimeException newInstance(String message) {
            return new UnexpectedFailureException(message);
        }
    },

    UNSUPPORTED_OPERATION {
        @Override
        public RuntimeException newInstance(String message) {
            return new UnsupportedOperationException(message);
        }
    };

    public abstract RuntimeException newInstance(String message);

}
