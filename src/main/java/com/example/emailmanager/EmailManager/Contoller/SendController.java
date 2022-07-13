package com.example.emailmanager.EmailManager.Contoller;

import com.example.emailmanager.EmailManager.Service.EmailService;
import com.example.emailmanager.Model.Email;
import com.example.emailmanager.EmailManager.Service.SendService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/send")
public class SendController {

    @Autowired
    SendService sendService;

    @Autowired
    EmailService emailService;

    @Autowired
    ObjectMapper mapper;


    @PostMapping(value = "/simple")
    ResponseEntity sendSimpleEmail(@RequestBody Email email){
        Map<String, String> response = new HashMap<>();
        try {
            Long emailId = sendService.sendSimpleEmail(email);


            response.put("emailId", emailId.toString());
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("ErrorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping(value = "/html")
    ResponseEntity sendHtml(@RequestBody Email email){
        Map<String, String> response = new HashMap<>();
        try {
            Long emailId = emailService.save(email);
            response.put("emailId", emailId.toString());
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("ErrorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
