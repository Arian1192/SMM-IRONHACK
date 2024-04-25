package com.ssm.systemmeetmanagement.controller;

import com.ssm.systemmeetmanagement.model.Appointment;
import com.ssm.systemmeetmanagement.service.interfaces.IAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final IAppointmentService appointmentService;

//    @PostMapping("/new_appointment")
//    public ResponseEntity<Appointment> createNewAppointment(@RequestBody Appointment appointment){
//        return ResponseEntity.ok(appointmentService.save(appointment));
//    }

}
