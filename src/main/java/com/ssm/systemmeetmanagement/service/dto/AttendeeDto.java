package com.ssm.systemmeetmanagement.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AttendeeDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Set<RoleDto> roles;
}

