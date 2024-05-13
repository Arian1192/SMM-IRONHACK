package com.ssm.systemmeetmanagement.repository;

import com.ssm.systemmeetmanagement.model.Appointment;
import com.ssm.systemmeetmanagement.model.Attendee;
import com.ssm.systemmeetmanagement.model.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AttendeeRepositoryTest {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    private Attendee dummyAttendee;



    @BeforeEach
    void setUp() {
        Role user = new Role("user");
        roleRepository.save(user);
        Set<Role> role = new HashSet<>();
        role.add(user);
        Set<Appointment> appointments = new HashSet<>();
        dummyAttendee = new Attendee("Arian", "Collaso","arian.collaso.rodriguez@gmail.com", "patata", role , appointments );
        attendeeRepository.save(dummyAttendee);
    }


    @AfterEach
    void tearDown() {
        attendeeRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        Attendee maybeAttendee = attendeeRepository.findByEmail("arian.collaso.rodriguez@gmail.com").orElseThrow();
        assertEquals("Arian", maybeAttendee.getName());
        assertEquals("Collaso", maybeAttendee.getSurname());
        assertEquals("patata", maybeAttendee.getPassword());
        assertEquals(0, maybeAttendee.getAppointments().size());
    }

    @Test
    void findByName() {
        Attendee maybeAttendee = attendeeRepository.findByName("Arian").orElseThrow();
        assertEquals("arian.collaso.rodriguez@gmail.com", maybeAttendee.getEmail());
        assertEquals("Collaso", maybeAttendee.getSurname());
        assertEquals("patata", maybeAttendee.getPassword());
        assertEquals(0, maybeAttendee.getAppointments().size());
    }
}