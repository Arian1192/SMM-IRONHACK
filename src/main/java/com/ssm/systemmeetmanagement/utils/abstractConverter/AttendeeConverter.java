package com.ssm.systemmeetmanagement.utils.abstractConverter;

import com.ssm.systemmeetmanagement.model.Attendee;
import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.repository.RoleRepository;
import com.ssm.systemmeetmanagement.service.dto.AttendeeDto;
import com.ssm.systemmeetmanagement.service.dto.RoleDto;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class AttendeeConverter extends AbstractConverter<Attendee, AttendeeDto>{

    private final Set<RoleDto> roles = new HashSet<>();

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Attendee fromDto(AttendeeDto dto) {
        return null;
    }

    @Override
    public AttendeeDto fromEntity(Attendee entity) {
        AttendeeDto attendeeDto = new AttendeeDto();
        attendeeDto.setId(entity.getId());
        attendeeDto.setName(entity.getName());
        attendeeDto.setSurname(entity.getSurname());
        attendeeDto.setEmail(entity.getEmail());
        if (entity.getRoles() != null) {
            Set<RoleDto> rolesToAdd = new HashSet<>();
            for(Role role: entity.getRoles()) {
                RoleDto roleDto = new RoleConverter().fromEntity(role);
                rolesToAdd.add(roleDto);
            }
            attendeeDto.setRoles(rolesToAdd);
        } else {
            // Si la colección de roles es null, puedes asignar una lista vacía o manejarlo de otra manera según tu lógica de negocio
            return null;
        }

        return attendeeDto;
    }

}
