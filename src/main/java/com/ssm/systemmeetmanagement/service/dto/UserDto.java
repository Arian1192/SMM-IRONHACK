package com.ssm.systemmeetmanagement.service.dto;

import lombok.*;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto {
    private  String name;
    private  String surname;
    private  String email;
    private Set<RoleDto> roles;
    private boolean isEnabled;
    private boolean accountNoExpired;
    private boolean credentialNoExpired;
    private boolean accountNoLocked;

}
