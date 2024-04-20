package com.ssm.systemmeetmanagement.controller.auth;

import com.ssm.systemmeetmanagement.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String name;
    String surname;
    String email;
    Set<Role> roles;
}
