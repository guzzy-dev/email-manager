package com.example.emailmanager.Model;

import com.example.emailmanager.utils.Convertors.JsonToMapConverter;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
@Getter
@Setter
@ToString
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


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "html_template",referencedColumnName = "name", nullable = false)
    private Template htmlTemplate;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    @Convert(attributeName = "content", converter = JsonToMapConverter.class)
    private Map<String, String> content;

    private int priority;

    public void setHtmlTemplate(Template htmlTemplate) {
        this.htmlTemplate = htmlTemplate;
        this.priority = htmlTemplate.getPriority();
    }
}
