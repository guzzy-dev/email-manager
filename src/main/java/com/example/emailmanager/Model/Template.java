package com.example.emailmanager.Model;

import com.example.emailmanager.utils.Convertors.StringToListConverter;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "templates")
@Data
public class Template implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String templateName;

    @Column(name = "dependencies")
    @Convert(converter = StringToListConverter.class)
    private List<String> dependencies;

    private int priority;

    public Template(String templateName){
        this.templateName = templateName;
    }

    public Template() {

    }


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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
