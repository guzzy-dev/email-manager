package com.example.emailmanager.Contoller;

import com.example.emailmanager.Service.Repository.Entity.Template;
import com.example.emailmanager.Service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/templates")
public class TemplateController {

    @Autowired
    TemplateService templateService;

    @PostMapping(value = "/")
    ResponseEntity addTemplate(@RequestBody Template template){
        return ResponseEntity.ok(templateService.save(template));
    }

    @GetMapping(value = "/name/{templateName}")
    ResponseEntity findByName(@PathVariable String templateName){

        Template template = templateService.fetchByName(templateName);
        return ResponseEntity.ok(template);
    }
}
