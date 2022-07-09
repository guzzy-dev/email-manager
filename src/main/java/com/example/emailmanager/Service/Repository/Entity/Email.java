package com.example.emailmanager.Service.Repository.Entity;

import com.example.emailmanager.utils.JsonToMapConverter;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Table(name = "emails")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "emailto")
    private String emailTo;

    @Column(name = "emailfrom")
    private String emailFrom;

    private String text;

    @Column(name = "title")
    private String subject;

    @Column(name = "html_template")
    private String htmlTemplate;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    @Convert(attributeName = "content", converter = JsonToMapConverter.class)
    private Map<String, String> content;

    @Value("CREATED")
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getHtmlTemplate() {
        return htmlTemplate;
    }

    public void setHtmlTemplate(String htmlTemplate) {
        this.htmlTemplate = htmlTemplate;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", emailTo='" + emailTo + '\'' +
                ", emailFrom='" + emailFrom + '\'' +
                ", text='" + text + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
