package com.sourcery.planningpoker.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.liquibase")
public class LiquibaseConfig {
}
