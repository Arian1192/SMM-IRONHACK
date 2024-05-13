package com.ssm.systemmeetmanagement.service.implementations;

import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.service.dto.AppointmentDto;
import com.ssm.systemmeetmanagement.service.dto.AttendeeDto;
import com.ssm.systemmeetmanagement.service.interfaces.IEmailService;
import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import services.Courier;
import services.SendService;

import java.io.IOException;
import java.util.HashMap;

@Service
public class EmailServiceImplementation implements IEmailService {
    @Override
    public void sendPromotedUserEmail(String name, String email, Role role) {
        Courier.init(System.getenv("TOKEN"));
        SendEnhancedRequestBody request = new SendEnhancedRequestBody();
        SendRequestMessage message = new SendRequestMessage();

        HashMap<String, String> to = new HashMap<>();
        to.put("email", email);
        message.setTo(to);
        message.setTemplate("CA3YGSXZARMDJ9Q4T02BQ30MERZ3");
        HashMap<String, Object> data = new HashMap<>();
        data.put("role", role.getName());
        data.put("name", name);
        message.setData(data);

        request.setMessage(message);
        try {
            SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(request);
            System.out.println(response);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    @Value("${app.courier.token}")
    private String token;

    @Override
    public void sendNewUserEmail(String name, String email, String password) {

        System.out.println(token);
        Courier.init(System.getenv("TOKEN"));

        SendEnhancedRequestBody request = new SendEnhancedRequestBody();
        SendRequestMessage message = new SendRequestMessage();

        HashMap<String, String> to = new HashMap<>();
        to.put("email", email);
        message.setTo(to);
        message.setTemplate("2BPVSV0Z99482RM64RY43TWTNA9H");

        HashMap<String, Object> data = new HashMap<>();
        data.put("recipientName", name);
        data.put("name", name);
        data.put("autoPassword", password);
        message.setData(data);

        request.setMessage(message);
        try {
            SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(request);
            System.out.println(response);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    @Override
    public void sendNewAppointmentCreatedForAttendees(AppointmentDto appointmentDto, AttendeeDto attendeeDto) {
        Courier.init(System.getenv("TOKEN"));
        SendEnhancedRequestBody request = new SendEnhancedRequestBody();
        SendRequestMessage message = new SendRequestMessage();
        HashMap<String, String > to = new HashMap<>();
        to.put("email", attendeeDto.getEmail());
        message.setTo(to);
        message.setTemplate("KWS3CF4ZXKMSZQGZPN4TACV5FJ72");
        HashMap<String, Object> data = new HashMap<>();
        data.put("host" , appointmentDto.getHost().getName());
        data.put("title", appointmentDto.getTitle());
        data.put("description", appointmentDto.getDescription());
        data.put("date", appointmentDto.getLocalDate().toString());
        data.put("time", appointmentDto.getLocalTime().toString());
        data.put("location", appointmentDto.getLocation());
        data.put("isOnline", appointmentDto.isOnline());
        data.put("isOnsite", appointmentDto.isOnsite());
        data.put("duration", appointmentDto.getDuration());
        message.setData(data);
        request.setMessage(message);
        try {
            SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(request);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendNewAppointmentCreatedForHost(AppointmentDto appointment) {
        Courier.init(System.getenv("TOKEN"));

        SendEnhancedRequestBody request = new SendEnhancedRequestBody();
        SendRequestMessage message = new SendRequestMessage();
        HashMap<String, String > to = new HashMap<>();
        to.put("email", appointment.getHost().getEmail());
        message.setTo(to);
        message.setTemplate("S8VD7Q58MF43QHPYMH59Z5SYYCCW");
        HashMap<String, Object> data = new HashMap<>();
        data.put("host", appointment.getHost().getName());
        data.put("title", appointment.getTitle());
        data.put("description", appointment.getDescription());
        StringBuilder attendeesList = new StringBuilder();
        for (AttendeeDto attendeeDto : appointment.getAttendees()) {
            attendeesList.append("- ").append(attendeeDto.getEmail()).append("\n");
        }
        data.put("attendees", attendeesList.toString());
        data.put("date", appointment.getLocalDate().toString());
        data.put("time", appointment.getLocalTime().toString());
        data.put("location", appointment.getLocation());
        data.put("isOnline", appointment.isOnline());
        data.put("isOnsite", appointment.isOnsite());
        data.put("duration", appointment.getDuration());
        message.setData(data);
        request.setMessage(message);
        try {
            SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(request);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

}};
