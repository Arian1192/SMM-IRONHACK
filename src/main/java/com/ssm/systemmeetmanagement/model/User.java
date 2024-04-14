package com.ssm.systemmeetmanagement.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    @ManyToMany(cascade = CascadeType.MERGE)
    private Collection<Role> roles;


    public User(String name, String surname, String email, String password, Collection<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
