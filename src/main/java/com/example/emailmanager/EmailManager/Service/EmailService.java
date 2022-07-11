package com.example.emailmanager.EmailManager.Service;


import com.example.emailmanager.EmailManager.Service.Repository.EmailRepository;
import com.example.emailmanager.Model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmailService {
    @Autowired
    EmailRepository emailRepository;

    public Optional<Email> fetchById(Long id){
        return emailRepository.findById(id);
    }

    public Long save(Email email) throws SQLException {
        emailRepository.save(email);
        return email.getId();
    }

    public void deleteById(Long id){
        emailRepository.deleteById(id);
    }

    public List<Email> getAllSortedByPriority(){
        return new ArrayList<>();
    }
}
