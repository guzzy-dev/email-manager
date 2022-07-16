package com.example.emailmanager.EmailManager.Repository;

import com.example.emailmanager.Model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {

    public Optional<Template> findByTemplateName(String name);

}
