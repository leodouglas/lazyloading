package com.techandsolve.lazyloading.exception;

/**
 * Exception when weights have not been defined
 * Created by leo on 10/03/17.
 */
public class UndefinedWeightsException extends LazyLoadingException {

    @Override
    public String getMessage() {
        return "The weights have not been defined";
    }
}
