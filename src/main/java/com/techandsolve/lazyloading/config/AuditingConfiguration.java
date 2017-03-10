package com.techandsolve.lazyloading.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by leo on 10/03/17.
 * Configuration for enable auditing in JPA
 */
@Configuration
@EnableJpaAuditing
public class AuditingConfiguration {
}
