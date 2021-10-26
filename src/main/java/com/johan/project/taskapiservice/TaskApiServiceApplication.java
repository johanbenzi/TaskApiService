package com.johan.project.taskapiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TaskApiServiceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(TaskApiServiceApplication.class, args);
    }
}
