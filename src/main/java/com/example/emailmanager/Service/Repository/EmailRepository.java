package com.example.emailmanager.Service.Repository;

import com.example.emailmanager.Service.Repository.Entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    public List<Email> findEmailsByStatus(String status);
}
