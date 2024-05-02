package com.ssm.systemmeetmanagement.utils.abstractConverter;

import com.ssm.systemmeetmanagement.model.PermissionEntity;
import com.ssm.systemmeetmanagement.service.dto.PermissionEntityDto;

public class PermissionConverter extends AbstractConverter<PermissionEntity, PermissionEntityDto> {
    @Override
    public PermissionEntity fromDto(PermissionEntityDto dto) {
        return null;
    }

    @Override
    public PermissionEntityDto fromEntity(PermissionEntity entity) {
        PermissionEntityDto permissionEntityDto = new PermissionEntityDto();
        permissionEntityDto.setName(entity.getName());
        return permissionEntityDto;
    }
}
