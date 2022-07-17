package com.example.emailmanager.EmailManager.Service;

import com.example.emailmanager.Model.Email;
import com.example.emailmanager.Model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.InterfaceAddress;
import java.util.List;
import java.util.Map;


@Service
public class SendService {

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    TemplateService templateService;

    @Autowired
    Transport transport;

    @Autowired
    Session session;


    public Long sendSimpleEmail(Email email) throws Exception{
        InternetAddress[] addresses = {new InternetAddress(email.getEmailTo())};

        MimeMessage mimeMessage = new MimeMessage(session);

        mimeMessage.setRecipients(Message.RecipientType.TO, addresses);
        mimeMessage.setFrom(new InternetAddress(email.getEmailFrom()));
        mimeMessage.setText(email.getText());
        mimeMessage.setSubject(email.getSubject());

        transport.sendMessage(mimeMessage, addresses);
        return email.getId();
        //TODO add logging of email
    }

    public Long sendHTMLEmail(Email email) throws Exception{
        InternetAddress[] addresses = {new InternetAddress(email.getEmailTo())};

        Template template = templateService.fetchByName(email.getHtmlTemplate().getTemplateName());
        email.setHtmlTemplate(template);
        checkTemplateDependencies(template.getDependencies(), email.getContent());

        Context htmlContext = new Context();
        htmlContext.setVariable("content", email.getContent());

        String process = templateEngine.process(email.getHtmlTemplate().getTemplateName(), htmlContext);


        MimeMessage mimeMessage = new MimeMessage(session);
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setTo(email.getEmailTo());
        helper.setText(process, true);
        helper.setSubject(email.getSubject());
        helper.setFrom(email.getEmailFrom());

        transport.sendMessage(helper.getMimeMessage(), addresses);
        return email.getId();
        //TODO add logging of email
    }


    private final void checkTemplateDependencies(List<String> requiredDependencies, Map<String, String> inputContent) throws Exception{
        for(String dependency: requiredDependencies){
            if(! inputContent.containsKey(dependency)){
                throw new Exception(dependency + " attribute is missing in content");
            }
        }

    }
}
