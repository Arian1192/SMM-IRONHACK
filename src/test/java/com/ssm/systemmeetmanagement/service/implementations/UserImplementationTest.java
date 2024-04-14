package com.ssm.systemmeetmanagement.service.implementations;

import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.model.User;
import com.ssm.systemmeetmanagement.service.interfaces.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserImplementationTest {

    @Autowired
    private IUserService userService;
    @BeforeEach
    void setUp() {
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");
        Collection<Role> collectionsOfRoles = new ArrayList<>(Arrays.asList(adminRole, userRole));
        User newUser = new User("Arian", "Collaso", "arian.collaso.rodrigues@gmail.com", "password", collectionsOfRoles );
        userService.save(newUser);

    }

//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void test_findAllUsers() {
//
//    }
//
//    @Test
//    void test_findAllUsersByRoles() {
//    }
//
//    @Test
//    void test_findUserById() {
//    }
}