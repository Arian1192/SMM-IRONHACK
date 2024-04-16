package com.ssm.systemmeetmanagement.service.dto;

import com.ssm.systemmeetmanagement.model.PermissionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RoleDto {
    private  String name;
    private Set<PermissionEntityDto> permissionEntityDto;

    public RoleDto(String name, Set<PermissionEntityDto> permissionEntityDto){
        setPermissionEntityDto(permissionEntityDto);
        setName(name);
    }
}
