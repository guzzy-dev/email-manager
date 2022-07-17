package com.example.emailmanager.EmailHandler.Service;

import com.example.emailmanager.EmailManager.Service.ArchivedEmailService;
import com.example.emailmanager.EmailManager.Service.EmailService;
import com.example.emailmanager.EmailManager.Service.SendService;
import com.example.emailmanager.Model.ArchivedEmail;
import com.example.emailmanager.Model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HandleService {


    private boolean sending = true;

    @Autowired
    EmailService emailService;

    @Autowired
    ArchivedEmailService archivedEmailService;

    @Autowired
    SendService sendService;


    @Scheduled(fixedDelayString = "${handler.delay}")
    private void handleLaunch(){
        if(sending){
            handleEmails();
        }
    }

    private int handleEmails(){
        List<Email> allEmails = emailService.getAllSortedByPriority();
        for(Email email: allEmails){
            try {
                sendOneEmail(email);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return allEmails.size();
    }

    private void sendOneEmail(Email email) throws Exception {
        if(email.getHtmlTemplate() == null){
            sendService.sendSimpleEmail(email);
        }
        else{
            sendService.sendHTMLEmail(email);
        }

        archivedEmailService.save(new ArchivedEmail(email));
        emailService.delete(email);
        //TODO add logging
    }

    public void setSendingState(boolean status){
        sending = status;
        //TODO add logging
    }
}
