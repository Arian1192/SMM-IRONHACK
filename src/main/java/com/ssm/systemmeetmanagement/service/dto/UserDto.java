package com.ssm.systemmeetmanagement.service.dto;

import lombok.*;

import java.util.Collection;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto {
    private  String name;
    private  String surname;
    private  String email;
    private  String password;
    private Set<RoleDto> roles;
    private boolean isEnabled;
    private boolean isAccountNoExpired;
    private boolean isCredentialNoExpired;
    private boolean isAccountNoLocked;

}
