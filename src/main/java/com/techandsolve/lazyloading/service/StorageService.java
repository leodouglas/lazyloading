package com.techandsolve.lazyloading.service;

import com.techandsolve.lazyloading.exception.StorageFileFailedException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Service to storage
 * Created by leo on 10/03/17.
 */
public interface StorageService {

    /**
     * create a new file path
     * @return new path
     */
    Path createNewFile();

    /**
     * init storage
     */
    void init();

    /**
     * store a file
     * @param file
     * @return path
     * @throws StorageFileFailedException Exception to failed process file
     */
    Path store(MultipartFile file) throws StorageFileFailedException;

    /**
     * resolve filename to path
     * @param filename
     * @return path
     */
    Path load(String filename);

    /**
     * convert path to spring resource
     * @param path
     * @return resource
     */
    Resource loadAsResource(Path path);

    /**
     * empty storage folder
     */
    void deleteAll();

}
