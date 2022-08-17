package com.example.emailmanager.Config;

import com.example.emailmanager.EmailManager.Service.SendService;
import com.example.emailmanager.utils.Threads.SendingThread;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;


import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Configuration
public class SenderConfig {

    @Autowired
    Environment environment;

    @Autowired
    SendService sendService;


    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(thymeleafTemplateResolver());
        return templateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver() {
        SpringResourceTemplateResolver templateResolver
                = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }

    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    @Bean
    @Scope("prototype")
    Transport transport(Session session) throws MessagingException {

        Transport transport = session.getTransport("smtp");
        transport.connect(environment.getProperty("email.sender.host"), environment.getProperty("email.sender.username"), environment.getProperty("email.sender.password"));

        return transport;
    }

    @Bean
    Session session(){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", environment.getProperty("email.sender.host"));
        properties.put("mail.smtp.port", environment.getProperty("email.sender.port"));
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", environment.getProperty("email.sender.host"));
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        properties.put("mail.debug", environment.getProperty("email.sender.debug"));

        Session session = Session.getInstance(properties);
        return session;
    }

    @Bean
    List<Transport> transportList() throws MessagingException {
        List<Transport> list = new ArrayList<>();
        for(int i = 0; i <Integer.valueOf(environment.getProperty("email.sender.threads")) ; i++){
            list.add(transport(session()));
        }
        return list;
    }

    @Bean
    List<SendingThread> sendingThreadList() throws MessagingException {
        List<SendingThread> list = new ArrayList<>();
        for(int i = 0; i <Integer.valueOf(environment.getProperty("email.sender.threads")) ; i++){
            SendingThread thread = new SendingThread(transport(session()), i+1);
            thread.setSendService(sendService);
            list.add(thread);
        }
        return list;
    }

}
