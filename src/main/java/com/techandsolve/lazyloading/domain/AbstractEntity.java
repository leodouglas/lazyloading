package com.techandsolve.lazyloading.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Abstract Entity with createdDate, modifiedDate and Id field
 * Created by leo on 10/03/17.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}