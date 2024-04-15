package com.ssm.systemmeetmanagement.utils;

import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.service.dto.RoleDto;

public class RoleConverter extends AbstractConverter<Role, RoleDto> {
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
        return roleDto;
    }
}
