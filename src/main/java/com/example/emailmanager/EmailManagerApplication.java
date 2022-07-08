package com.example.emailmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"com.example.emailmanager"})
@EnableJpaRepositories("com.example.emailmanager.Service.Repository")
@EntityScan("com.example.emailmanager.Service.Repository.Entity")
public class EmailManagerApplication {


    public static void main(String[] args) {
        SpringApplication.run(EmailManagerApplication.class, args);
    }


}
