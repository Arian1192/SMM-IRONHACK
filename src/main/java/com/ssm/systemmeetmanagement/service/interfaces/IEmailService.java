package com.ssm.systemmeetmanagement.service.interfaces;

import com.ssm.systemmeetmanagement.model.Role;

public interface IEmailService {
    void sendNewUserEmail(String name, String email, String password);

    void sendPromotedUserEmail(String name, String email, Role role);
}
