package com.example.emailmanager.Contoller;

import com.example.emailmanager.Service.EmailService;
import com.example.emailmanager.Service.Repository.Entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping(value = "/")
    Long saveEmail(@RequestBody Email email) throws SQLException {
        emailService.save(email);
        return email.getId();
    }

}
