package com.ssm.systemmeetmanagement.controller;

import com.ssm.systemmeetmanagement.model.Appointment;
import com.ssm.systemmeetmanagement.model.Attendee;
import com.ssm.systemmeetmanagement.model.User;
import com.ssm.systemmeetmanagement.repository.UserRepository;
import com.ssm.systemmeetmanagement.service.dto.AppointmentDto;
import com.ssm.systemmeetmanagement.service.interfaces.IAppointmentService;
import com.ssm.systemmeetmanagement.service.interfaces.IAttendeeService;
import com.ssm.systemmeetmanagement.service.interfaces.IUserService;
import com.ssm.systemmeetmanagement.utils.abstractConverter.AppointmentConverter;
import com.ssm.systemmeetmanagement.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final IAppointmentService appointmentService;

    private final IUserService userService;

    private final UserRepository userRepository;

    private final IAttendeeService attendeeService;

    @GetMapping(value = "/get_appointment/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable long id){
        Optional<Appointment> maybeAppointment = appointmentService.getAppointmentById(id);
        if(maybeAppointment.isPresent()){
            Appointment appointment = maybeAppointment.get();
            AppointmentDto appointmentDto = new AppointmentConverter().fromEntity(appointment);
            return ResponseEntity.ok(appointmentDto);
        }else{
            return ResponseUtils.notFoundResponse("Appointment with id: " + id + " not found");
        }
    }

    @GetMapping(value = "/get_allAppointments")
    public ResponseEntity<?> getAllAppointments(){
        List<Appointment> maybeListOfAppointments = appointmentService.getAllAppointments();
        if(maybeListOfAppointments.isEmpty()){
            return ResponseUtils.noContentResponse("No appointments are created");
        }else{
            List<AppointmentDto> listOffAppointmentsDto = new ArrayList<>();
            for(Appointment appointment : maybeListOfAppointments){
                AppointmentDto appointmentDto = new AppointmentConverter().fromEntity(appointment);
                listOffAppointmentsDto.add(appointmentDto);
            }
            return ResponseEntity.ok(listOffAppointmentsDto);
        }
    }



    @PostMapping("/new_appointment")
    public ResponseEntity<AppointmentDto> createNewAppointment(@RequestBody Appointment appointment){
        Set<Attendee> attendees = appointment.getAttendees();
        Set<Attendee> updatedAttendees = new HashSet<>();

        Attendee hostAttendee = appointment.getHost();
        Optional<Attendee> foundHost = attendeeService.findUserById(hostAttendee.getId());

        if(foundHost.isPresent()){
            appointment.setHost(hostAttendee);
        }else{
            User host = userService.findUserById(hostAttendee.getId()).orElseThrow();
            Attendee attendee = new Attendee();
            attendee.setUser(host);
            userRepository.deleteById(host.getId());
            attendeeService.save(attendee);
            appointment.setHost(attendee);
        }

        for(Attendee attendee : attendees){
            Long attendeeId = attendee.getId();
            Optional<Attendee> foundAttendee = attendeeService.findUserById(attendeeId);
            if(foundAttendee.isPresent()){
                updatedAttendees.add(foundAttendee.get());
            } else {
                Long userId = attendee.getId();
                Optional<User> foundUser = userService.findUserById(userId);
                if(foundUser.isPresent()){
                    User user = foundUser.get();
                    Attendee userAttendee = new Attendee();
                    userAttendee.setUser(user);
                    userRepository.deleteById(user.getId());
                    attendeeService.save(userAttendee);
                    updatedAttendees.add(userAttendee);
                } else {
                }
            }
        }

        appointment.setAttendees(updatedAttendees);
        Appointment savedAppointment = appointmentService.save(appointment);
        AppointmentDto savedAppointmentDto = new AppointmentConverter().fromEntity(savedAppointment);

        return ResponseEntity.ok(savedAppointmentDto);
    }

    @PutMapping(value = "/update_appointment/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable long id, @RequestBody Appointment updateAppointment) throws IllegalAccessException {
        Optional<Appointment> maybeAppointment = appointmentService.getAppointmentById(id);

        if(maybeAppointment.isPresent()){
            Appointment appointment = maybeAppointment.get();
            Attendee hostAttendee = appointment.getHost();
            if (updateAppointment.getHost() != null && updateAppointment.getHost().getId() != null) {
                hostAttendee = attendeeService.findUserById(updateAppointment.getHost().getId()).orElseThrow();
            }
            for (Field field : updateAppointment.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(updateAppointment);
                if (value != null && !field.getName().equals("host")) {
                    field.set(appointment, value);
                }
            }
            appointment.setHost(hostAttendee);
            appointmentService.save(appointment);
            AppointmentDto appointmentDto = new AppointmentConverter().fromEntity(appointment);
            return ResponseEntity.ok(appointmentDto);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping(value = "/delete_appointment/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable long id) {
        Optional<Appointment> appointmentToDelete = appointmentService.getAppointmentById(id);
        if (appointmentToDelete.isPresent()) {
            appointmentService.deleteAppointmentById(id);
            return ResponseUtils.noContentResponse("Appointment was delete successfully");
        } else {
            return ResponseUtils.notFoundResponse("Appointment with id: " + id + " not found");
        }
    }



}
