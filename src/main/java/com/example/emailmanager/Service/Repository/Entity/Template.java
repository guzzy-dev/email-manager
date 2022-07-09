package com.example.emailmanager.Service.Repository.Entity;

import com.example.emailmanager.utils.Convertors.StringToListConverter;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "templates")
@Data
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String templateName;

    @Column(name = "dependencies")
    @Convert(converter = StringToListConverter.class)
    private List<String> dependencies;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }
}
