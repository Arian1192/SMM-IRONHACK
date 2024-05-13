package com.ssm.systemmeetmanagement.service.interfaces;

import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.service.dto.AppointmentDto;
import com.ssm.systemmeetmanagement.service.dto.AttendeeDto;

public interface IEmailService {
    void sendNewUserEmail(String name, String email, String password);

    void sendPromotedUserEmail(String name, String email, Role role);

    void sendNewAppointmentCreatedForAttendees(AppointmentDto appointmentDto, AttendeeDto attendeeDto);

    void sendNewAppointmentCreatedForHost(AppointmentDto appointmentDto);

}
