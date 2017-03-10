package com.techandsolve.lazyloading.exception;

/**
 * Exception to fail initialize storage
 * Created by leo on 10/03/17.
 */
public class StorageInitializeFailedException extends LazyLoadingException {

    @Override
    public String getMessage() {
        return "Could not initialize storage";
    }
}
