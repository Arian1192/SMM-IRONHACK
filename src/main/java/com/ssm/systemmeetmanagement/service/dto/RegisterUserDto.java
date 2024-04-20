package com.ssm.systemmeetmanagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegisterUserDto {
    String name;
    String surname;
    String email;
    Set<RoleDto> roles;
}
