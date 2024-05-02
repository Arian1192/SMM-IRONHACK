package com.ssm.systemmeetmanagement.utils.abstractConverter;

import com.ssm.systemmeetmanagement.model.PermissionEntity;
import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.service.dto.PermissionEntityDto;
import com.ssm.systemmeetmanagement.service.dto.RoleDto;

import java.util.HashSet;
import java.util.Set;

public class RoleConverter extends AbstractConverter<Role, RoleDto> {

    private final Set<PermissionEntityDto> permissions = new HashSet<>();

    @Override
    public Role fromDto(RoleDto dto) {
        Role role = new Role();
        role.setName(dto.getName());
        return role;
    }

    @Override
    public RoleDto fromEntity(Role entity) {
        RoleDto roleDto = new RoleDto();
        roleDto.setName(entity.getName());
        for(PermissionEntity permissionEntity: entity.getPermissionEntityList()){
            PermissionEntityDto permissionEntityDto = new PermissionConverter().fromEntity(permissionEntity);
            permissions.add(permissionEntityDto);
        }
        roleDto.setPermissionEntityDto(permissions);
        return roleDto;
    }
}
