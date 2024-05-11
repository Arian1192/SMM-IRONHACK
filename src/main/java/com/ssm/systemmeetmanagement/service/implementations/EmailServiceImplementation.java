package com.ssm.systemmeetmanagement.service.implementations;

import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.service.interfaces.IEmailService;
import lombok.extern.slf4j.Slf4j;
import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import org.slf4j.Logger;
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

}
