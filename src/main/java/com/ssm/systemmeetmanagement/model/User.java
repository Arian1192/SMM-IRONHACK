package com.ssm.systemmeetmanagement.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "You must supply a user name")
    private String name;
    @NotEmpty(message = "You must supply a user surname")
    private String surname;
    @Email(message = "You must supply a valid email", regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")
    @NotEmpty(message = "You must supply a email")
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
