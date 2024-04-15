package com.ssm.systemmeetmanagement.utils;

import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.model.User;
import com.ssm.systemmeetmanagement.service.dto.RoleDto;
import com.ssm.systemmeetmanagement.service.dto.UserDto;


import java.util.ArrayList;
import java.util.Collection;

public class UserConverter extends AbstractConverter<User, UserDto> {

    @Override
    public User fromDto(UserDto dto) {
        User user = new User();
        Collection<Role> roles = new ArrayList<>();
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
        user.setPassword(dto.getPassword());
        if(roles.isEmpty()){
            user.setRoles(null);
        }
        return user;
    }

    @Override
    public UserDto fromEntity(User entity) {
        UserDto user = new UserDto();
        Collection<RoleDto> roleDto = new ArrayList<>();
        for(Object roleObj: entity.getRoles()){
            if(roleObj instanceof RoleDto role){
                roleDto.add(role);
            }
        }
        user.setEmail(entity.getEmail());
        user.setName(entity.getName());
        user.setPassword(entity.getPassword());
        user.setRoles(roleDto);
        return user;
    }
}
