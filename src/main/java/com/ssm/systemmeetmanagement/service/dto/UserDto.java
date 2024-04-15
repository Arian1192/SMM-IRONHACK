package com.ssm.systemmeetmanagement.service.dto;

import lombok.*;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto {
    private  String name;
    private  String surname;
    private  String email;
    private  String password;
    private  Collection<RoleDto> roles;

}
