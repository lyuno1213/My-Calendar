package com.example.mycalendar.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("io.bootify.my_calendar.domain")
@EnableJpaRepositories("io.bootify.my_calendar.repos")
@EnableTransactionManagement
public class DomainConfig {
}
