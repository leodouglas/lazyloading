package com.techandsolve.lazyloading.service.impl;

import com.techandsolve.lazyloading.domain.CommandType;
import com.techandsolve.lazyloading.domain.LoadedFile;
import com.techandsolve.lazyloading.domain.LoadedFileLine;
import com.techandsolve.lazyloading.domain.LoadedFileResult;
import com.techandsolve.lazyloading.exception.NegativeWeightException;
import com.techandsolve.lazyloading.exception.UndefinedWeightsException;
import com.techandsolve.lazyloading.repository.LoadedFileLineRepository;
import com.techandsolve.lazyloading.repository.LoadedFileRepository;
import com.techandsolve.lazyloading.repository.LoadedFileResultRepository;
import com.techandsolve.lazyloading.service.CommandLineService;
import com.techandsolve.lazyloading.service.LazyLoadingService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;

/**
 * Implementation for CommandLineService
 * Created by leo on 10/03/17.
 */
@Service
public class CommandLineServiceImpl implements CommandLineService {

    @Inject
    private LazyLoadingService lazyLoadingService;

    @Inject
    private LoadedFileRepository repository;

    @Inject
    private LoadedFileLineRepository lineRepository;

    @Inject
    private LoadedFileResultRepository resultRepository;

    @Override
    public List<String> process(String idNumber, String fileName, List<Integer> inputs) throws NegativeWeightException, UndefinedWeightsException {
        LoadedFile file = repository.save(new LoadedFile(fileName, idNumber));
        List<String> output = new ArrayList<>();

        Iterator<Integer> inputIter = inputs.iterator();
        int tc = inputIter.next();
        lineRepository.save(new LoadedFileLine(file, tc, CommandType.COUNT_DAYS));
        for (int t = 0; t < tc; t++) {
            LoadedFileResult fileResult = resultRepository.save(new LoadedFileResult(file, t + 1, dailyProcess(file, inputIter)));
            output.add(String.format("Case #%d: %d", fileResult.getNumCase(), fileResult.getValue()));
        }
        return output;
    }

    private int dailyProcess(LoadedFile file, Iterator<Integer> inputIter) throws NegativeWeightException, UndefinedWeightsException {
        lazyLoadingService.startWeights();
        int n = inputIter.next();
        lineRepository.save(new LoadedFileLine(file, n, CommandType.COUNT_DATA));
        for (int i = 0; i < n; i++) {
            Integer weight = inputIter.next();
            if (lazyLoadingService.addWeight(weight)) {
                lineRepository.save(new LoadedFileLine(file, weight, CommandType.DATA));
            }
        }
        return lazyLoadingService.calc();
    }
}
