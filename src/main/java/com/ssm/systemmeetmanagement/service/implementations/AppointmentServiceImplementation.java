package com.ssm.systemmeetmanagement.service.implementations;

import com.ssm.systemmeetmanagement.model.Appointment;
import com.ssm.systemmeetmanagement.repository.AppointmentRepository;
import com.ssm.systemmeetmanagement.service.interfaces.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AppointmentServiceImplementation implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public Optional<List<Appointment>> getAllAppointmentsByHost(String hostName) {
        return appointmentRepository.findAllAppointmentByHostName(hostName);
    }
    @Override
    public Optional<Set<Appointment>> getAllAppointmentsByDate(LocalDate date) {
        return Optional.empty();
    }

    @Override
    public void deleteAppointmentById(long id) {
        appointmentRepository.deleteById(id);
    }
}
