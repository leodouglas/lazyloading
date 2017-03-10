package com.techandsolve.lazyloading.controller;

import com.techandsolve.lazyloading.exception.NegativeWeightException;
import com.techandsolve.lazyloading.exception.UndefinedWeightsException;
import com.techandsolve.lazyloading.service.CommandLineService;
import com.techandsolve.lazyloading.service.StorageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 10/03/17.
 * Controller used to LazyLoading requests
 */
@Controller
@RequestMapping("/")
public class LazyLoadingController {

    @Inject
    private CommandLineService service;

    @Inject
    private StorageService storageService;

    /**
     *
     * @param input
     * @return plain text output
     * @throws NegativeWeightException if any value is negative
     * @throws UndefinedWeightsException If any value is undefined
     */
    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity commands(@RequestBody String input) throws NegativeWeightException, UndefinedWeightsException {
        List<Integer> dataInput = new ArrayList<>();

        BufferedReader b = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
        b.lines().forEach(it -> {
            if (!it.isEmpty()) {
                dataInput.add(Integer.valueOf(it));
            }
        });

        String text = StringUtils.join(service.process("", "plain text", dataInput), "\n");
        return ResponseEntity.ok().body(text);
    }

    /**
     *
     * @param idNumber identification number
     * @param file file to process
     * @return download file output
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/upload")
    public ResponseEntity<Resource> serveFile(@RequestParam("idNumber") String idNumber, @RequestParam("file") MultipartFile file) throws IOException {
        Path responseFile = storageService.createNewFile();
        Path requestFile = storageService.store(file);

        try (BufferedWriter writer = Files.newBufferedWriter(responseFile)) {
            List<Integer> dataInput = new ArrayList<>();
            Files.lines(requestFile).forEach(it -> {
                if (!it.isEmpty()) {
                    dataInput.add(Integer.valueOf(it));
                }
            });
            writer.write(StringUtils.join(service.process(idNumber, responseFile.getFileName().toString(), dataInput), "\n"));
        }

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s.txt\"", responseFile.getFileName()))
                .body(storageService.loadAsResource(responseFile));
    }

    /**
     * index request to html
     * @return html template
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
