package com.fenrir.guruguru_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GuruguruSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuruguruSpringApplication.class, args);
    }

}
