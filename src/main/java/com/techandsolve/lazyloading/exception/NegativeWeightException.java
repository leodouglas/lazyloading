package com.techandsolve.lazyloading.exception;

/**
 * Exception when value can not be negative
 * Created by leo on 10/03/17.
 */
public class NegativeWeightException extends LazyLoadingException {

    @Override
    public String getMessage() {
        return "The value can not be negative";
    }
}
