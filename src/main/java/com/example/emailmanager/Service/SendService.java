package com.example.emailmanager.Service;

import com.example.emailmanager.Service.Repository.Entity.Email;
import com.example.emailmanager.Service.Repository.Entity.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Map;


@Service
public class SendService {

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    EmailService emailService;

    @Autowired
    TemplateService templateService;

    @Autowired
    JavaMailSender sender;

    public Long sendSimpleEmail(Email email) throws Exception{

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getEmailTo());
        message.setText(email.getText());
        message.setSubject(email.getSubject());
        message.setFrom(email.getEmailFrom());

        sender.send(message);
        return emailService.save(email);
    }

    public Long sendHTMLEmail(Email email) throws Exception{
        Template template = templateService.fetchByName(email.getHtmlTemplate());
        checkTemplateDependencies(template.getDependencies(), email.getContent());

        Context htmlContext = new Context();
        htmlContext.setVariable("content", email.getContent());

        String process = templateEngine.process(email.getHtmlTemplate(), htmlContext);

        javax.mail.internet.MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setTo(email.getEmailTo());
        helper.setText(process, true);
        helper.setSubject(email.getSubject());
        helper.setFrom(email.getEmailFrom());

        sender.send(mimeMessage);
        return emailService.save(email);
    }


    private final void checkTemplateDependencies(List<String> requiredDependencies, Map<String, String> inputContent) throws Exception{
        for(String dependency: requiredDependencies){
            if(! inputContent.containsKey(dependency)){
                throw new Exception(dependency + " attribute is missing in content");
            }
        }

    }
}
