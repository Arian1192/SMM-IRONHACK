package com.ssm.systemmeetmanagement.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RoleDto {
    private  String name;

    public RoleDto(String name){
        setName(name);
    }
}
