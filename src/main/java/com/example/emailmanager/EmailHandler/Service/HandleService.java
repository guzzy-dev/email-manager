package com.example.emailmanager.EmailHandler.Service;

import com.example.emailmanager.EmailManager.Service.ArchivedEmailService;
import com.example.emailmanager.EmailManager.Service.EmailService;
import com.example.emailmanager.EmailManager.Service.SendService;
import com.example.emailmanager.Model.ArchivedEmail;
import com.example.emailmanager.Model.Email;
import com.example.emailmanager.utils.Threads.SendingThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.Transport;
import java.util.List;

@Service
public class HandleService {


    private boolean sending = false;

    @Autowired
    EmailService emailService;

    @Autowired
    ArchivedEmailService archivedEmailService;

    @Autowired
    SendService sendService;


    @Autowired
    @Qualifier("sendingThreadList")
    List<SendingThread> transportList;


    @Scheduled(fixedDelayString = "${handler.delay}")
    private void handleLaunch(){
        if(sending){
            handleEmails();
        }
    }

    private int handleEmails(){
        List<Email> allEmails = emailService.getAllSortedByPriority();
        if(allEmails.isEmpty()) System.out.println("================================================================================================== DONE");
        int transportCount = transportList.size();
        int transportNumber = 0;
        for(Email email: allEmails){
//            try {
//                sendOneEmail(email, transportList.get(transportNumber));
//                transportNumber++;
//                if(transportNumber >= transportCount) transportNumber = 0; //circled iteration in list
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            transportList.get(transportNumber).setEmail(email);
            transportList.get(transportNumber).run();

            archivedEmailService.save(new ArchivedEmail(email));
            emailService.delete(email);

            transportNumber++;
            if(transportNumber >= transportCount) transportNumber = 0;
        }
        return allEmails.size();
    }

    private void sendOneEmail(Email email, Transport transport) throws Exception {
        if (email.getHtmlTemplate() == null) {
            sendService.sendSimpleEmail(email, transport);
        }
        else{
            sendService.sendHTMLEmail(email, transport);
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
