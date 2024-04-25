package com.ssm.systemmeetmanagement.service.implementations;

import com.ssm.systemmeetmanagement.model.Appointment;
import com.ssm.systemmeetmanagement.model.Attendee;
import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.repository.AppointmentRepository;
import com.ssm.systemmeetmanagement.repository.AttendeeRepository;
import com.ssm.systemmeetmanagement.repository.RoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentServiceImplementationTest {

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    private  Attendee attendee;

    private Set<Attendee> listOfAttendees = new HashSet<>();

    private Appointment dummyAppointment;

    private Attendee host;

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

        host = new Attendee("Arian", "Collaso","arian.collaso.rodriguez@gmail.com", "patata", rolesHost , null );
        attendee = new Attendee("Pedro", "Collaso","Pedro.collaso.rodriguez@gmail.com", "patito", rolesAttendee , null );

        attendeeRepository.save(host);
        attendeeRepository.save(attendee);
        listOfAttendees.add(attendee);

        host.setAppointments(new HashSet<>());
        attendee.setAppointments(new HashSet<>());

        dummyAppointment = new Appointment("example", "example", host, new HashSet<>(), LocalDate.now(), LocalTime.now(), "office", false, true, "120" );
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
    void save() {
        Appointment appointment = new Appointment("example 2", "example 2", host, new HashSet<>(), LocalDate.now(), LocalTime.now(), "office", false, true, "120" );
        appointment.setAttendees(listOfAttendees);
        appointmentRepository.save(appointment);
        assertEquals(2, appointmentRepository.findAll().size());
        appointmentRepository.findById(appointment.getId()).ifPresent(appointment1 -> {
            assertEquals("example 2", appointment1.getTitle());
        });
    }

    @Test
    void getAllAppointments() {
        Appointment appointment = new Appointment("example 2", "example 2", host, new HashSet<>(), LocalDate.now(), LocalTime.now(), "office", false, true, "120" );
        appointment.setAttendees(listOfAttendees);
        appointmentRepository.save(appointment);
        assertEquals(2, appointmentRepository.findAll().size());

    }

    @Test
    void getAppointmentById() {
        Appointment appointment = new Appointment("example 2", "example 2", host, new HashSet<>(), LocalDate.now(), LocalTime.now(), "office", false, true, "120" );
        appointment.setAttendees(listOfAttendees);
        appointmentRepository.save(appointment);
        appointmentRepository.findById(appointment.getId()).ifPresent(appointment1 -> {
            assertEquals(appointment.getTitle(), appointment1.getTitle());
        });
    }

    @Test
    void getAllAppointmentsByHost() {
        Optional<List<Appointment>> maybeListOfAppointments = appointmentRepository.findAllAppointmentByHostName("Arian");
        if(maybeListOfAppointments.isPresent()){
            assertEquals(1, maybeListOfAppointments.get().size());
        }
    }

    @Test
    void getAllAppointmentsByDate() {
        LocalDate date = dummyAppointment.getDate();
        Optional<List<Appointment>> maybeListOfAppointments = appointmentRepository.findAllAppointmentsByDate(date);
        maybeListOfAppointments.ifPresent(appointments -> {
            assertEquals(dummyAppointment.getDate(), appointments.get(0).getDate());
        });
    }
}