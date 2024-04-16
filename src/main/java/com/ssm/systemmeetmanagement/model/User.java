package com.ssm.systemmeetmanagement.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.mapping.Join;


import java.util.Set;

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
    @Column(unique = true)
    private String email;
    private String password;
    @Column(name = "is_enabled")
    private boolean isEnabled;
    @Column(name = "account_No_Expired")
    private boolean accountNoExpired;
    @Column(name = "account_No_Locked")
    private boolean accountNoLocked;
    @Column(name = "credential_NO_Expired")
    private boolean credentialNoExpired;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    public User(String name, String surname, String email, String password, Set<Role> roles) {
        setEmail(email);
        setPassword(password);
        setName(name);
        setSurname(surname);
        setRoles(roles);
    }

    public User(String name, String surname, String email, String password, boolean isEnabled, boolean accountNoExpired, boolean accountNoLocked, boolean credentialNoExpired, Set<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
        this.accountNoExpired = accountNoExpired;
        this.accountNoLocked = accountNoLocked;
        this.credentialNoExpired = credentialNoExpired;
        this.roles = roles;
    }

    public User(String name, String surname, String email) {
        setName(name);
        setSurname(surname);
        setEmail(email);
    }
}
