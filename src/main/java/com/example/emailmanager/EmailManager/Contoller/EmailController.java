package com.example.emailmanager.EmailManager.Contoller;

import com.example.emailmanager.EmailManager.Service.EmailService;
import com.example.emailmanager.Model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;


    @GetMapping(value = "/{emailId}")
    ResponseEntity<Email> getEmailById(@PathVariable Long emailId){
        return ResponseEntity.ok(emailService.fetchById(emailId).get());
    }


}
