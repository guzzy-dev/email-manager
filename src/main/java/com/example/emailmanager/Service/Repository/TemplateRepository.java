package com.example.emailmanager.Service.Repository;

import com.example.emailmanager.Service.Repository.Entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {

    public Optional<Template> findByTemplateName(String name);

}
