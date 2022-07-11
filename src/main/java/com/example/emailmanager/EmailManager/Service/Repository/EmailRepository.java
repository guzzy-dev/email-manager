package com.example.emailmanager.EmailManager.Service.Repository;

import com.example.emailmanager.Model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {


}
