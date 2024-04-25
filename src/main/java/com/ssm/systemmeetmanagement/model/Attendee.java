package com.ssm.systemmeetmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "attendee")
public class Attendee extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @ManyToMany(mappedBy = "attendees", fetch = FetchType.EAGER)
    private Set<Appointment> appointments = new HashSet<>();


    public Attendee(String name, String surname, String email, String password,  Set<Role> role, Set<Appointment> appointments ){
        super(name, surname, email, password, role);
        this.appointments = appointments;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        // Solo utilizar propiedades que estén disponibles sin necesidad de inicialización perezosa
        return result;
    }

}
