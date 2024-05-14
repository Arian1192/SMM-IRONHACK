package com.ssm.systemmeetmanagement.utils.abstractConverter;

import com.ssm.systemmeetmanagement.model.PermissionEntity;
import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.model.User;
import com.ssm.systemmeetmanagement.service.dto.PermissionEntityDto;
import com.ssm.systemmeetmanagement.service.dto.RoleDto;
import com.ssm.systemmeetmanagement.service.dto.UserDto;


import java.util.*;

public class UserConverter extends AbstractConverter<User, UserDto> {

    @Override
    public User fromDto(UserDto dto) {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        if(dto.getRoles() != null){
        for(Object roleObj: dto.getRoles()){
            if(roleObj instanceof Role role){
                roles.add(role);
            }
        }
        }
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        if(roles.isEmpty()){
            user.setRoles(null);
        }
        return user;
    }

    @Override
    public UserDto fromEntity(User entity) {
        UserDto user = new UserDto();
        Set<RoleDto> roleDtos = new HashSet<>();

        for(Role role: entity.getRoles()){
            RoleDto roleDto = new RoleDto(role.getName(), convertPermissionEntitiesToDtos(role.getPermissionEntityList()));
            roleDtos.add(roleDto);
        }
        user.setId(entity.getId());
        user.setEmail(entity.getEmail());
        user.setSurname(entity.getSurname());
        user.setName(entity.getName());
        user.setRoles(roleDtos);
        return user;
    }

    private Set<PermissionEntityDto> convertPermissionEntitiesToDtos(Set<PermissionEntity> permissionEntities) {
        Set<PermissionEntityDto> permissionEntityDtos = new HashSet<>();

        for (PermissionEntity permissionEntity : permissionEntities) {
            PermissionEntityDto permissionEntityDto = new PermissionEntityDto(permissionEntity.getName());
            permissionEntityDtos.add(permissionEntityDto);
        }

        return permissionEntityDtos;
    }

//    private Set<PermissionEntity> convertPermissionEntitiesDtoToEntity(Set<PermissionEntityDto> permissionEntitiesDto) {
//        Set<PermissionEntity> permissionEntity = new HashSet<>();
//
//        for (PermissionEntityDto permissionEntityDto : permissionEntitiesDto) {
//            PermissionEntity permission = new PermissionEntity(permissionEntityDto.getName());
//            permissionEntity.add(permission);
//        }
//
//        return permissionEntity;
//    }

}
