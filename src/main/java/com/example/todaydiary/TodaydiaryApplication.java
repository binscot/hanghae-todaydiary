package com.example.todaydiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TodaydiaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodaydiaryApplication.class, args);
    }

}
