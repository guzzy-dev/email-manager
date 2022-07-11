package com.example.emailmanager.EmailManager.Service;


import com.example.emailmanager.Model.Template;
import com.example.emailmanager.EmailManager.Service.Repository.TemplateRepository;
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

    public Template fetchByName(String name) throws Exception {
        try{
            System.out.println(name);
            Template template = templateRepository.findByTemplateName(name).get();
            return template;
        }
        catch (Exception e){
            throw new Exception("Can't find template with name " + name);
        }


    }
}
