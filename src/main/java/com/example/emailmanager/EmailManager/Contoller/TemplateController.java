package com.example.emailmanager.EmailManager.Contoller;

import com.example.emailmanager.Model.Template;
import com.example.emailmanager.EmailManager.Service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/templates")
@RolesAllowed({"ROLE_CONFIGURER"})
public class TemplateController {

    @Autowired
    TemplateService templateService;

    @PostMapping(value = "/")
    ResponseEntity addTemplate(@RequestBody Template template){
        return ResponseEntity.ok(templateService.save(template));
    }

    @GetMapping(value = "/name/{templateName}")
    ResponseEntity findByName(@PathVariable String templateName){
        Map<String, String> response = new HashMap<>();
        try {
            Template template = templateService.fetchByName(templateName);
            return ResponseEntity.ok(template);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("ErrorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}
