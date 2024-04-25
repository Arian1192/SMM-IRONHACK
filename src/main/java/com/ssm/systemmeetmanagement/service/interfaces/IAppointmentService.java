package com.ssm.systemmeetmanagement.service.interfaces;
import com.ssm.systemmeetmanagement.model.Appointment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IAppointmentService {
//    Appointment save(Appointment appointment);
    List<Appointment> getAllAppointments();
    Optional<Appointment> getAppointmentById(Long id);
    Optional<List<Appointment>> getAllAppointmentsByHost(String hostName);

    Optional<Set<Appointment>> getAllAppointmentsByDate(LocalDate date);

}
