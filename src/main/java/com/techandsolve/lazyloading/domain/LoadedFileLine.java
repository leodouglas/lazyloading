package com.techandsolve.lazyloading.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Table with items processed
 * Created by leo on 10/03/17.
 */
@Entity
@Getter
@ToString
public class LoadedFileLine extends AbstractEntity {

    @ManyToOne
    private LoadedFile loadedFile;

    @NotNull
    private Integer command;

    @NotNull
    private CommandType commandType;

    private LoadedFileLine() {
    }

    public LoadedFileLine(LoadedFile loadedFile, Integer command, CommandType commandType) {
        this.loadedFile = loadedFile;
        this.command = command;
        this.commandType = commandType;
    }
}