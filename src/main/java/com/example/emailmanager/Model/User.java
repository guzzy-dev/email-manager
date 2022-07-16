package com.example.emailmanager.Model;


import com.example.emailmanager.utils.Convertors.EnumListToStringConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;

    private String password;

    private boolean enable;

    @Column(name = "roles")
    @Convert(converter = EnumListToStringConverter.class)
    private List<Role> roles;

}
