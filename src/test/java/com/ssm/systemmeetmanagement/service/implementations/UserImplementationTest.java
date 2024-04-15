package com.ssm.systemmeetmanagement.service.implementations;

import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.model.User;
import com.ssm.systemmeetmanagement.repository.RoleRepository;
import com.ssm.systemmeetmanagement.repository.UserRepository;
import com.ssm.systemmeetmanagement.service.interfaces.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserImplementationTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private static Role adminRole;

    private static User newUser;

    @BeforeEach
    void setUp() {
        adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");
        roleRepository.saveAll(Arrays.asList(adminRole, userRole));
        Collection<Role> collectionsOfRoles = new ArrayList<>(Arrays.asList(adminRole, userRole));
        newUser = new User("Arian", "Collaso", "arian.collaso.rodrigues@gmail.com", "password", collectionsOfRoles );
        userService.save(newUser);

    }
    @AfterEach
   void tearDown() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void test_findAllUsers() {
        Optional<List<User>> maybeListOfUser = userService.findAllUsers();
        if(maybeListOfUser.isPresent()){
            List<User> listOfUser = maybeListOfUser.get();
            assertEquals("Arian", listOfUser.get(0).getName());
        }
    }

    @Test
    void test_findAllUsersByRoles() {
        Optional<List<User>> maybeListOfUser = userService.findAllUsersByRoles(adminRole);
        if(maybeListOfUser.isPresent()){
            List<User> listOfUser = maybeListOfUser.get();
            assertEquals("Arian", listOfUser.get(0).getName());
        }
    }

    @Test
    void test_findUserById() {
        Optional<User> user = userService.findUserById(newUser.getId());
        user.ifPresent(userFound -> assertEquals("Arian", userFound.getName()));
    }
}