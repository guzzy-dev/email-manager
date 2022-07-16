package com.example.emailmanager.EmailHandler.API;

import com.example.emailmanager.EmailHandler.Service.HandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/send_handler")
@RolesAllowed({"ROLE_ADMIN"})
public class EmailHandlerController {

    @Autowired
    HandleService handleService;

    @PostMapping(value = "/start")
    private ResponseEntity enableSending(){
        handleService.setSendingState(true);
        return ResponseEntity.ok("sending started");
    }

    @PostMapping(value = "/stop")
    private ResponseEntity disableSending(){
        handleService.setSendingState(false);
        return ResponseEntity.ok("sending stopped");
    }

}
