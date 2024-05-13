package com.ssm.systemmeetmanagement.service.implementations;


import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.model.User;
import com.ssm.systemmeetmanagement.repository.RoleRepository;
import com.ssm.systemmeetmanagement.repository.UserRepository;
import com.ssm.systemmeetmanagement.service.interfaces.IUserService;
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

    @Override
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findUserByEmail(String email){
       return userRepository.findByEmail(email);
    }

}
