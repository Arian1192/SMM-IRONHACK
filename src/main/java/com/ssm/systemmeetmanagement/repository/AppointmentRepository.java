package com.ssm.systemmeetmanagement.repository;

import com.ssm.systemmeetmanagement.model.Appointment;
import com.ssm.systemmeetmanagement.model.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a.host FROM Appointment a WHERE a.id = ?1")
    Optional<Attendee> findHostByAppointmentId(Long id);

    @Query("SELECT a.attendees FROM Appointment a WHERE a.id = ?1")
    Optional<Set<Attendee>> findAllAttendeesByAppointmentId(Long id);

    @Query("SELECT a FROM Appointment a INNER JOIN a.host b WHERE a.host.name = ?1")
    Optional<List<Appointment>> findAllAppointmentByHostName(String name);

    @Query("SELECT a FROM Appointment a WHERE a.date = ?1")
    Optional<List<Appointment>> findAllAppointmentsByDate(LocalDate date);

}
