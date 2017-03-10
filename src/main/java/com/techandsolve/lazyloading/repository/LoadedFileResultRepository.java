package com.techandsolve.lazyloading.repository;

import com.techandsolve.lazyloading.domain.LoadedFileResult;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository to LoadedFileResult table
 * Created by leo on 10/03/17.
 */
public interface LoadedFileResultRepository extends CrudRepository<LoadedFileResult, Long> {

}
