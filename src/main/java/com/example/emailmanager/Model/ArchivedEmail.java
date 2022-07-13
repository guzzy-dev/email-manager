package com.example.emailmanager.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "archived_emails")
@Getter
@Setter
@ToString
public class ArchivedEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    private String emailTo;

    private String emailFrom;

    private String title;

    private String text;

    private String html_template;

    private String content;

    public ArchivedEmail(Email email) {
        this.id = email.getId();
        this.emailTo = email.getEmailTo();
        this.emailFrom = email.getEmailFrom();
        this.title = email.getSubject();
        this.text = email.getText();
        this.html_template = email.getHtmlTemplate().getTemplateName();
        this.content = email.getContent().toString();
    }

    public ArchivedEmail() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
