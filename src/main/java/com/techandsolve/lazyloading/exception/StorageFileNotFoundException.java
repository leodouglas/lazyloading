package com.techandsolve.lazyloading.exception;

/**
 * Exception to file not found
 * Created by leo on 10/03/17.
 */
public class StorageFileNotFoundException extends LazyLoadingException {

    private String fileName;

    public StorageFileNotFoundException() {
    }

    public StorageFileNotFoundException(String filename) {
        this.fileName = filename;
    }

    @Override
    public String getMessage() {
        return String.format("Could not read file: %s", fileName);
    }
}
