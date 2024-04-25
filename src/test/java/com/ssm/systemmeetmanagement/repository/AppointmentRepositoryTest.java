package com.ssm.systemmeetmanagement.repository;

import com.ssm.systemmeetmanagement.model.Appointment;
import com.ssm.systemmeetmanagement.model.Attendee;
import com.ssm.systemmeetmanagement.model.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    private Appointment dummyAppointment;

    private  Set<Attendee> listOfAttendees = new HashSet<>();
    private Attendee  attendee;
    @BeforeEach
    void setUp() {
        Role userRole = new Role("USER");
        Role adminRole = new Role("ADMIN");
        roleRepository.save(userRole);
        roleRepository.save(adminRole);

        Set<Role> rolesHost = new HashSet<>();
        Set<Role> rolesAttendee = new HashSet<>();
        rolesHost.add(adminRole);
        rolesAttendee.add(userRole);

        Attendee host = new Attendee("Arian", "Collaso","arian.collaso.rodriguez@gmail.com", "patata", rolesHost , null );
        attendee = new Attendee("Pedro", "Collaso","Pedro.collaso.rodriguez@gmail.com", "patito", rolesAttendee , null );

        attendeeRepository.save(host);
        attendeeRepository.save(attendee);
        listOfAttendees.add(attendee);

        host.setAppointments(new HashSet<>());
        attendee.setAppointments(new HashSet<>());

        dummyAppointment = new Appointment("example", "example", host, listOfAttendees, LocalDate.now(), LocalTime.now(), "office", false, true, "120" );
        dummyAppointment.setAttendees(listOfAttendees);
        appointmentRepository.save(dummyAppointment);
    }



    @AfterEach
    void tearDown() {
        appointmentRepository.deleteAll();
        attendeeRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void findHostByAppointmentId() {
        Attendee maybeHost = appointmentRepository.findHostByAppointmentId(dummyAppointment.getId()).orElseThrow();
        assertEquals("Arian", maybeHost.getName());

    }

    @Test
    void findAllAttendeesByAppointmentId() {
       Optional<Set<Attendee>> maybeAttendees = appointmentRepository.findAllAttendeesByAppointmentId(dummyAppointment.getId());
       maybeAttendees.ifPresent(attendees -> assertEquals("Pedro", attendees.stream().findFirst().get().getName() ));
    }


}