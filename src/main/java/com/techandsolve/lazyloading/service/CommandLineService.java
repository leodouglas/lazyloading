package com.techandsolve.lazyloading.service;

import com.techandsolve.lazyloading.exception.NegativeWeightException;
import com.techandsolve.lazyloading.exception.UndefinedWeightsException;

import java.util.List;

/**
 * Service to process command line
 * Created by leo on 10/03/17.
 */
public interface CommandLineService {

    /**
     * Init process
     * @param idNumber identification number
     * @param fileName file to process
     * @param inputs values to process
     * @return process result
     * @throws NegativeWeightException if any value is negative
     * @throws UndefinedWeightsException If any value is undefined
     */
    List<String> process(String idNumber, String fileName, List<Integer> inputs) throws NegativeWeightException, UndefinedWeightsException;
}
