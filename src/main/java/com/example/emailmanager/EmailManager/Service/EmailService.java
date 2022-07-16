package com.example.emailmanager.EmailManager.Service;


import com.example.emailmanager.EmailManager.Repository.EmailRepository;
import com.example.emailmanager.Model.Email;
import com.example.emailmanager.Model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {
    @Autowired
    EmailRepository emailRepository;

    @Autowired
    TemplateService templateService;

    public Optional<Email> fetchById(Long id){
        return emailRepository.findById(id);
    }

    public Long save(Email email) throws Exception {
        Template template = templateService.fetchByName(email.getHtmlTemplate().getTemplateName());
        email.setHtmlTemplate(template);

        emailRepository.save(email);
        return email.getId();
    }

    public void delete(Email email){
        emailRepository.delete(email);
    }

    public List<Email> getAllSortedByPriority(){
        //returns first 50 high-priority emails from queue
        return emailRepository.findTop50ByOrderByPriorityDesc();
    }
}
