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
@Table(name = "attendee")
public class Attendee extends User{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Setter(AccessLevel.NONE)
//    private Long id;
    @ManyToMany(mappedBy = "attendees", fetch = FetchType.EAGER)
    private Set<Appointment> appointments = new HashSet<>();

    public Attendee(String name, String surname, String email, String password,  Set<Role> role, Set<Appointment> appointments ){
        super(name, surname, email, password, role);
        this.appointments = appointments;
    }


//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        Attendee attendee = (Attendee) obj;
//        return id != null && id.equals(attendee.id);
//    }


}
