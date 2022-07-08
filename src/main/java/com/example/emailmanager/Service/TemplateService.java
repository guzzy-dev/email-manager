package com.example.emailmanager.Service;


import com.example.emailmanager.Service.Repository.Entity.Template;
import com.example.emailmanager.Service.Repository.TemplateRepository;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TemplateService {
    @Autowired
    TemplateRepository templateRepository;

    public Template fetchById(Long id){
        return templateRepository.findById(id).get();
    }
    public Long save(Template template){
        Optional<Template> maybeTemplate =  templateRepository.findByTemplateName(template.getTemplateName());
        if(maybeTemplate.isEmpty()){
            templateRepository.save(template);
        }
        else{
            template.setId(maybeTemplate.get().getId());
            templateRepository.save(template);
        }

        return template.getId();
    }

    public Template fetchByName(String name){
        return templateRepository.findByTemplateName(name).get();
    }
}
