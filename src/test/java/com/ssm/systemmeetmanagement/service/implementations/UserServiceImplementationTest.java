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
class UserServiceImplementationTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private static Role adminRole;

    private Role userRole;

    private static User newUser;

    @BeforeEach
    void setUp() {
        adminRole = new Role("ADMIN");
        userRole = new Role("USER");
        roleRepository.saveAll(Arrays.asList(adminRole, userRole));
        Set<Role> setOfRoles = new HashSet<>(Arrays.asList(adminRole, userRole));
        newUser = new User("Arian", "Collaso", "arian.collaso.rodrigues@gmail.com", "password", setOfRoles );
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

    @Test
    void createNewUser() {
        User newUser = new User("Julian", "Perez", "julia.perez@gmail.com");
        userService.createNewUser(newUser);
        Optional<List<User>> maybeListOfUsers = userService.findAllUsers();
        if(maybeListOfUsers.isPresent()){
            List<User> listOfUser = maybeListOfUsers.get();
            Collection<Role> roles = listOfUser.get(1).getRoles();
            assertFalse(roles.isEmpty());
            assertTrue(roles.stream().anyMatch(role -> role.getName().equals("USER")));
        }
    }
}