package com.techandsolve.lazyloading.service;

import com.techandsolve.lazyloading.exception.NegativeWeightException;
import com.techandsolve.lazyloading.exception.UndefinedWeightsException;

/**
 * Service to calc lazy loading
 * Created by leo on 10/03/17.
 */
public interface LazyLoadingService {

    /**
     * add weight to calc
     * @param weight
     * @return if weight is valid
     * @throws NegativeWeightException if any value is negative
     */
    boolean addWeight(int weight) throws NegativeWeightException;

    /**
     * initialize vars to calc
     */
    void startWeights();

    /**
     * calc lazy loading weight
     * @return calc result
     * @throws UndefinedWeightsException If any value is undefined
     */
    int calc() throws UndefinedWeightsException;
}
