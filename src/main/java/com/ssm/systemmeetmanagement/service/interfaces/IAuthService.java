package com.ssm.systemmeetmanagement.service.interfaces;

import com.ssm.systemmeetmanagement.controller.auth.AuthResponse;
import com.ssm.systemmeetmanagement.controller.auth.LoginRequest;
import com.ssm.systemmeetmanagement.controller.auth.RegisterRequest;

public interface IAuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse register(RegisterRequest request);
}
