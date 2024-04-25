package com.ssm.systemmeetmanagement.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.mapping.Join;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "user")
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @NotEmpty(message = "You must supply a user name")
    private String name;
    @NotEmpty(message = "You must supply a user surname")
    private String surname;
    @Email(message = "You must supply a valid email", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @NotEmpty(message = "You must supply a email")
    @Column(unique = true)
    private String email;
    private String password;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> rolesAuthorities = this.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role : rolesAuthorities ){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }


    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(String name, String surname, String email, String password, Set<Role> roles) {
        setEmail(email);
        setPassword(password);
        setName(name);
        setSurname(surname);
        setRoles(roles);
    }

    public User(String name, String surname, String email) {
        setName(name);
        setSurname(surname);
        setEmail(email);
    }
}
