package com.techandsolve.lazyloading.service.impl;

import com.techandsolve.lazyloading.exception.NegativeWeightException;
import com.techandsolve.lazyloading.exception.UndefinedWeightsException;
import com.techandsolve.lazyloading.service.LazyLoadingService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation for LazyLoadingService
 * Created by leo on 10/03/17.
 */
@Service
@RequestScope
public class LazyLoadingServiceImpl implements LazyLoadingService {

    private List<Integer> weights = new ArrayList<>();

    @Override
    public boolean addWeight(int weight) throws NegativeWeightException {
        if (weight < 0) {
            throw new NegativeWeightException();
        }
        weights.add(weight);
        return true;
    }

    @Override
    public void startWeights(){
        weights.clear();
    }

    @Override
    public int calc() throws UndefinedWeightsException {
        if (weights.size() <= 0) {
            throw new UndefinedWeightsException();
        }
        Collections.sort(weights);
        int result = 0;
        int i = 0;
        int j = weights.size() - 1;
        while (true) {
            int max = weights.get(j);
            int sum = weights.get(j--);
            while (sum < 50 && i <= j) {
                sum += max;
                i++;
            }
            if (sum < 50) {
                break;
            }
            result++;
            if (i > j) {
                break;
            }
        }
        return result;
    }

}
