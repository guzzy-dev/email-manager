package com.example.emailmanager.EmailManager.Repository;

import com.example.emailmanager.Model.ArchivedEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivedEmailRepository extends JpaRepository<ArchivedEmail, Long> {

}
