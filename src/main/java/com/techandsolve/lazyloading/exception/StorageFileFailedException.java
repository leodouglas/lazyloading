package com.techandsolve.lazyloading.exception;

/**
 * Exception to failed process file
 * Created by leo on 10/03/17.
 */
public class StorageFileFailedException extends LazyLoadingException {

    private String fileName;

    public StorageFileFailedException() {
    }

    public StorageFileFailedException(String filename) {
        this.fileName = filename;
    }

    @Override
    public String getMessage() {
        return String.format("Failed process file %s", fileName);
    }
}
