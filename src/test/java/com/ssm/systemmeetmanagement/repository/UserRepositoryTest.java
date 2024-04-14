package com.ssm.systemmeetmanagement.repository;

import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private Role dummyRoleUser;
    private Role dummyRoleAdmin;

    private Collection<Role> collectionOfRoles = new ArrayList<>();

    private User dummyUser;


    @BeforeEach
    void setUp() {
        dummyRoleUser = new Role("USER");
        dummyRoleAdmin = new Role("ADMIN");
        collectionOfRoles.addAll(Arrays.asList(dummyRoleAdmin, dummyRoleUser));
        roleRepository.saveAll(Arrays.asList(dummyRoleUser, dummyRoleAdmin));
        dummyUser = new User("Arian", "Collaso", "arian.collaso.rodriguez@gmail.com", "patata", collectionOfRoles);
        userRepository.save(dummyUser);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void test_findAllUsersByRole(){
        Optional<List<User>> maybeListOfUser = userRepository.findAllByRoles(dummyRoleAdmin);
        if(maybeListOfUser.isPresent()){
            List<User> listOfUsers = maybeListOfUser.get();
            assertFalse(listOfUsers.isEmpty());
            assertEquals(1, listOfUsers.size());
        }
    }

    @Test
    void test_findAllUsers(){
        User newUser = new User("Ejemplo", "Ejemplo", "ejemplo@gmail.com", "ejemploPassword", collectionOfRoles);
        userRepository.save(newUser);
        Optional<List<User>> maybeListOfUser = Optional.of(userRepository.findAll());
        assertEquals(2, maybeListOfUser.get().size());

    }

    @Test
    void test_findOneUserById(){
        Optional<User> maybeUserFound = userRepository.findById(dummyUser.getId());
        maybeUserFound.ifPresent(user -> assertEquals(dummyUser.getName(), user.getName()));
    }
}
