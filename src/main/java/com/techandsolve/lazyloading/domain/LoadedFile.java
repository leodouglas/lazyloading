package com.techandsolve.lazyloading.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * Table LoadedFile to log process
 * Created by leo on 10/03/17.
 */
@Entity
@Getter
@ToString
public class LoadedFile extends AbstractEntity {

    private String fileName;

    private String idNumber;

    private LoadedFile() {
    }

    public LoadedFile(String fileName, String idNumber) {
        this.fileName = fileName;
        this.idNumber = idNumber;
    }
}