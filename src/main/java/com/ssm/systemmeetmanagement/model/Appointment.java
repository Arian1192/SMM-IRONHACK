package com.ssm.systemmeetmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointment")
@EqualsAndHashCode()
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private Attendee host;

    @ManyToMany
    @JoinTable(
            name = "appointment_attendee",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "attendee_id")
    )
    private Set<Attendee> attendees;

    private LocalDate date;
    private LocalTime time;
    private String location;
    private Boolean isOnline;
    private Boolean isOnsite;
    private String duration;

    public Appointment(String title, String description, Attendee host, Set<Attendee> listOfAttendees, LocalDate date, LocalTime time, String location, boolean isOnline, boolean isOnsite, String duration ){
        setTitle(title);
        setDescription(description);
        setHost(host);
        setAttendees(listOfAttendees);
        setDate(date);
        setTime(time);
        setLocation(location);
        setIsOnline(isOnline);
        setIsOnsite(isOnsite);
        setDuration(duration);
    }
}
