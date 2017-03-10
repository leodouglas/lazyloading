package com.techandsolve.lazyloading.exception;

/**
 * Abstract Exceptions
 * Created by leo on 10/03/17.
 */
public abstract class LazyLoadingException extends RuntimeException {

    public abstract String getMessage();

    @Override
    public String toString() {
        return getMessage();
    }
}
