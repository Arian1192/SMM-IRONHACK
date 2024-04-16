package com.ssm.systemmeetmanagement.service.interfaces;

import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    void save(User user);
    void createNewUser(User user);
    Optional<List<User>> findAllUsers();
    Optional<List<User>> findAllUsersByRoles(Role role);
    Optional<User> findUserById(Long id);
}
