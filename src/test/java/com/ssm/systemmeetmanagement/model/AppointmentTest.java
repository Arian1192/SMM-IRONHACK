package com.ssm.systemmeetmanagement.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    private Appointment dummyAppointment;
    @BeforeEach
    void setUp() {
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");
        Set<Role> hostRole = new HashSet<>();
        Set<Role> attendeeRole = new HashSet<>();
        hostRole.add(adminRole);
        attendeeRole.add(userRole);
        Set<Appointment> appointments = new HashSet<>();
        Attendee host = new Attendee("Arian", "Collaso","arian.collaso.rodriguez@gmail.com", "patata", hostRole, appointments );
        Set<Attendee> listOffAttendees = new HashSet<>();
        Attendee Attendee = new Attendee("Pedro", "Ramirez","pedro.ramirez@gmail.com", "potato", attendeeRole, appointments );
        listOffAttendees.add(Attendee);
        dummyAppointment = new Appointment("example", "example", host, listOffAttendees, LocalDate.now(), LocalTime.now(), "office", false, true, "120" );
    }


    @Test
    void getTitle() {
        assertEquals("example", dummyAppointment.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("example", dummyAppointment.getDescription());
    }

    @Test
    void getHost() {
        Attendee host = dummyAppointment.getHost();
        assertEquals("Arian", host.getName());
        assertEquals("arian.collaso.rodriguez@gmail.com", host.getEmail());
        Set<Role> roles = host.getRoles();
        Role adminRole = roles.stream().findFirst().get();
        assertEquals("ADMIN", adminRole.getName());
    }

    @Test
    void getAttendees() {
        Set<Attendee> listOfAttendees = dummyAppointment.getAttendees();
        listOfAttendees.stream().findFirst().ifPresent(person -> {
            assertEquals("Pedro", person.getName());
            assertEquals("Ramirez", person.getSurname());
            assertEquals("pedro.ramirez@gmail.com", person.getEmail());
            Set<Role> roles = person.getRoles();
            roles.stream().findFirst().ifPresent(role -> assertEquals("USER", role.getName()));
        });
    }

    @Test
    void getDate() {
        LocalDate appointmentDate = dummyAppointment.getDate();
        assertNotNull(appointmentDate);
    }

    @Test
    void getTime() {
        LocalTime appointmentTime = dummyAppointment.getTime();
        assertNotNull(appointmentTime);
    }

    @Test
    void getLocation() {
        assertEquals("office", dummyAppointment.getLocation());
    }

    @Test
    void getIsOnline() {
        assertFalse(dummyAppointment.getIsOnline());
    }

    @Test
    void getIsOnsite() {
        assertTrue(dummyAppointment.getIsOnsite());
    }

    @Test
    void getDuration() {
        assertEquals("120", dummyAppointment.getDuration());
    }

    @Test
    void setTitle() {
        dummyAppointment.setTitle("new title");
        assertEquals("new title", dummyAppointment.getTitle());
    }

    @Test
    void setDescription() {
        dummyAppointment.setDescription("new description");
        assertEquals("new description", dummyAppointment.getDescription());
    }

    @Test
    void setHost() {
        Role newRole = new Role("NEW_ROLE");
        Set<Role> newRoles = new HashSet<>();
        newRoles.add(newRole);
        Attendee newHost = new Attendee("New", "Host", "newhost@example.com", "password", newRoles, new HashSet<>());
        dummyAppointment.setHost(newHost);
        assertEquals("New", dummyAppointment.getHost().getName());
        assertEquals("newhost@example.com", dummyAppointment.getHost().getEmail());
    }

    @Test
    void setAttendees() {
        Set<Attendee> newAttendees = new HashSet<>();
        Attendee newAttendee = new Attendee("New", "Attendee", "newattendee@example.com", "password", new HashSet<>(), new HashSet<>());
        newAttendees.add(newAttendee);
        dummyAppointment.setAttendees(newAttendees);
        assertEquals(1, dummyAppointment.getAttendees().size());
        assertEquals("New", dummyAppointment.getAttendees().iterator().next().getName());
    }

    @Test
    void setDate() {
        LocalDate newDate = LocalDate.of(2024, 4, 23);
        dummyAppointment.setDate(newDate);
        assertEquals(newDate, dummyAppointment.getDate());
    }

    @Test
    void setTime() {
        LocalTime newTime = LocalTime.of(14, 30);
        dummyAppointment.setTime(newTime);
        assertEquals(newTime, dummyAppointment.getTime());
    }

    @Test
    void setLocation() {
        dummyAppointment.setLocation("new location");
        assertEquals("new location", dummyAppointment.getLocation());
    }

    @Test
    void setIsOnline() {
        dummyAppointment.setIsOnline(true);
        assertTrue(dummyAppointment.getIsOnline());
    }

    @Test
    void setIsOnsite() {
        dummyAppointment.setIsOnsite(false);
        assertFalse(dummyAppointment.getIsOnsite());
    }

    @Test
    void setDuration() {
        dummyAppointment.setDuration("60");
        assertEquals("60", dummyAppointment.getDuration());
    }

    @Test
    void testEquals() {
        Appointment sameAppointment = new Appointment("example", "example", dummyAppointment.getHost(), dummyAppointment.getAttendees(),
                dummyAppointment.getDate(), dummyAppointment.getTime(), dummyAppointment.getLocation(),
                dummyAppointment.getIsOnline(), dummyAppointment.getIsOnsite(), dummyAppointment.getDuration());
        assertTrue(dummyAppointment.equals(sameAppointment));
    }

    @Test
    void canEqual() {
        assertTrue(dummyAppointment.canEqual(new Appointment()));
    }

    @Test
    void testHashCode() {
        assertEquals(dummyAppointment.hashCode(), dummyAppointment.hashCode());
    }


}