package com.leonardorozza.mvgrreparacionesbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.leonardorozza.mvgrreparacionesbackend.persistence.entity")
@EnableJpaRepositories(basePackages = "com.leonardorozza.mvgrreparacionesbackend.persistence.repository")
@SpringBootApplication
public class MvgrReparacionesBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvgrReparacionesBackendApplication.class, args);
    }

}
