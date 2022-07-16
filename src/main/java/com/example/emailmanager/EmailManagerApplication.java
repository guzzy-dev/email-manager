package com.example.emailmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.example.emailmanager"})
public class EmailManagerApplication {


    public static void main(String[] args) {
        SpringApplication.run(EmailManagerApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
