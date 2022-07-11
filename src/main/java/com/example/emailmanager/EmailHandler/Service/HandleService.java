package com.example.emailmanager.EmailHandler.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class HandleService {



    @Scheduled(fixedDelay=3000)
    private void handleLaunch(){
        System.out.println("Hello World!");
    }

    private int handleEmails(){
        return 1;
    }
}
