package com.example.emailmanager.Service.Repository.Entity;

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

    private String dependencies;

    @Transient
    private List<String> dependenciesList;

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

    public List<String> getDependenciesList() {
        return dependenciesList;
    }

    public void setDependenciesList(List<String> dependenciesList) {
        this.dependencies = convertListToString(dependenciesList);
        this.dependenciesList = dependenciesList;
    }

    private String convertListToString(List<String> dependenciesList){
        String dependeciesString ="";
        for(int i = 0; i < dependenciesList.size(); i++){
            if(i == dependenciesList.size() - 1) {
                dependeciesString += dependenciesList.get(i);
                break;
            }
            dependeciesString += dependenciesList.get(i) + ",";
        }
        return dependeciesString;
    }
}
