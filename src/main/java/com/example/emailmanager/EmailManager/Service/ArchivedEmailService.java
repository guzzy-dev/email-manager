package com.example.emailmanager.EmailManager.Service;

import com.example.emailmanager.EmailManager.Repository.ArchivedEmailRepository;
import com.example.emailmanager.Model.ArchivedEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchivedEmailService {
    @Autowired
    ArchivedEmailRepository archivedEmailRepository;

    public void delete(ArchivedEmail email){
        archivedEmailRepository.delete(email);
    }

    public void save(ArchivedEmail email){
        archivedEmailRepository.save(email);
    }
}
