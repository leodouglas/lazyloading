package com.techandsolve.lazyloading.repository;

import com.techandsolve.lazyloading.domain.LoadedFile;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository to LoadedFile table
 * Created by leo on 10/03/17.
 */
public interface LoadedFileRepository extends CrudRepository<LoadedFile, Long> {

}
