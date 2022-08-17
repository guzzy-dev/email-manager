package com.example.emailmanager.utils.Threads;

import com.example.emailmanager.EmailManager.Service.SendService;
import com.example.emailmanager.Model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.Transport;


public class SendingThread implements Runnable{

    Transport transport;

    SendService sendService;

    Email email;

    int threadNumber;


    public SendingThread(Transport transport, int threadNumber) {
        this.transport = transport;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        try {
            if (email.getHtmlTemplate() == null) {
                sendService.sendSimpleEmail(email, transport);
            } else {
                sendService.sendHTMLEmail(email, transport);
            }
        }
        catch (Exception e){
            e.printStackTrace(); //TODO add logging
        }
        System.out.println(this);

    }

    @Override
    public String toString() {
        return "SendingThread{" +
                "threadNumber=" + threadNumber +
                '}';
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setSendService(SendService sendService) {
        this.sendService = sendService;
    }
}
