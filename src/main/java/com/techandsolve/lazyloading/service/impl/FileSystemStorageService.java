package com.techandsolve.lazyloading.service.impl;

import com.techandsolve.lazyloading.exception.StorageFileFailedException;
import com.techandsolve.lazyloading.exception.StorageFileNotFoundException;
import com.techandsolve.lazyloading.exception.StorageInitializeFailedException;
import com.techandsolve.lazyloading.service.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Implementation for StorageService
 * Created by leo on 10/03/17.
 */
@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation = Paths.get("upload-dir");

    @Override
    public Path createNewFile() {
        return this.rootLocation.resolve(UUID.randomUUID().toString());
    }

    @Override
    public Path store(MultipartFile file) throws StorageFileFailedException {
        Path newFile = createNewFile();
        try {
            if (file.isEmpty()) {
                throw new StorageFileFailedException(file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), newFile);
            return newFile;
        } catch (IOException e) {
            throw new StorageFileFailedException(file.getOriginalFilename());
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(Path path) {
        try {
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(path.getFileName().toString());

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException(path.getFileName().toString());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            if (Files.notExists(rootLocation)) {
                Files.createDirectory(rootLocation);
            }
        } catch (IOException e) {
            throw new StorageInitializeFailedException();
        }
    }
}
