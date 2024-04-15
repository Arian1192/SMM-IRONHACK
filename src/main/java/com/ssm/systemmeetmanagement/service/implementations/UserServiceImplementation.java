package com.ssm.systemmeetmanagement.service.implementations;

import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.model.User;
import com.ssm.systemmeetmanagement.repository.RoleRepository;
import com.ssm.systemmeetmanagement.repository.UserRepository;
import com.ssm.systemmeetmanagement.service.dto.RoleDto;
import com.ssm.systemmeetmanagement.service.interfaces.IUserService;
import com.ssm.systemmeetmanagement.utils.RoleConverter;
import com.ssm.systemmeetmanagement.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImplementation implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }


    @Override
    public void createNewUser(User user) {
        user.setRoles(Collections.singleton(roleRepository.findByName("USER").orElseGet(this::createAndSaveUserRole)));
        userRepository.save(user);
    }

    private Role createAndSaveUserRole(){
        RoleDto userRoleDto = new RoleDto("USER");
        return roleRepository.save(new RoleConverter().fromDto(userRoleDto));
    }

    @Override
    public Optional<List<User>> findAllUsers() {
        return Optional.of(userRepository.findAll());
    }

    @Override
    public Optional<List<User>> findAllUsersByRoles(Role role) {
        return userRepository.findAllByRoles(role);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
