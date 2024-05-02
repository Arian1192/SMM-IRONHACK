package com.ssm.systemmeetmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendee extends User{
    @ManyToMany(mappedBy = "attendees", fetch = FetchType.EAGER)
    private Set<Appointment> appointments = new HashSet<>();

    public Attendee(String name, String surname, String email, String password,  Set<Role> role, Set<Appointment> appointments ){
        super(name, surname, email, password, role);
        this.appointments = appointments;
    }

    public void setUser(User user){
        setEmail(user.getEmail());
        setName(user.getName());
        setSurname(getSurname());
        setRoles(user.getRoles());
        setPassword(user.getPassword());
    }

}
