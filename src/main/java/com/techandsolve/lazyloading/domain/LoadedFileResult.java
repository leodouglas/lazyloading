package com.techandsolve.lazyloading.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Table with result process
 * Created by leo on 10/03/17.
 */
@Entity
@Getter
@ToString
public class LoadedFileResult extends AbstractEntity {

    @ManyToOne
    private LoadedFile loadedFile;

    @NotNull
    private Integer numCase;

    @NotNull
    private Integer value;

    private LoadedFileResult() {
    }

    public LoadedFileResult(LoadedFile loadedFile, Integer numCase, Integer value) {
        this.loadedFile = loadedFile;
        this.numCase = numCase;
        this.value = value;
    }
}