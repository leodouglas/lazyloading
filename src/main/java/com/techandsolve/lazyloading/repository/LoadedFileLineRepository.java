package com.techandsolve.lazyloading.repository;

import com.techandsolve.lazyloading.domain.LoadedFileLine;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository to LoadedFileLine table
 * Created by leo on 10/03/17.
 */
public interface LoadedFileLineRepository extends CrudRepository<LoadedFileLine, Long> {

}
