package com.ssm.systemmeetmanagement.model;

import com.ssm.systemmeetmanagement.repository.AttendeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class AttendeeTest {

    private Attendee dummyAttendee;

    @BeforeEach
    void setUp() {
        Role newRole = new Role("USER");
        Set<Role> role = new HashSet<>();
        Set<Appointment> appointments = new HashSet<>();
        dummyAttendee = new Attendee("Arian", "Collaso", "arian.collaso.rodriguez@gmail.com","patata", role, appointments );
    }


    @Test
    void getName() {
           assertEquals("Arian", dummyAttendee.getName());
    }

    @Test
    void getSurname(){
        assertEquals("Collaso", dummyAttendee.getSurname());
    }

    @Test
    void getEmail(){
        assertEquals("arian.collaso.rodriguez@gmail.com", dummyAttendee.getEmail());
    }

    @Test
    void getPassword(){
        assertEquals("patata", dummyAttendee.getPassword());
    }

    @Test
    void getAppointments(){
        assertEquals(0, dummyAttendee.getAppointments().size());
    }

}