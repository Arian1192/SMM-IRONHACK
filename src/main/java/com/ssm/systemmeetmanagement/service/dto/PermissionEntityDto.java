package com.ssm.systemmeetmanagement.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PermissionEntityDto {

    private String name;

    public PermissionEntityDto(String name){
        this.name = name;
    }
}
