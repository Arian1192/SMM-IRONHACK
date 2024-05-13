package com.ssm.systemmeetmanagement.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class UserTest {

    static User dummyUser;

    @BeforeEach
    void setUp() {
        Role newRole = new Role( "USER");
        Set<Role> newSetRoles = new HashSet<>();
        newSetRoles.add(newRole);
        dummyUser = new User("Pedro", "Perez", "pedro.perez@gmail.com", "password", newSetRoles  );

    }

    @Test
    void getName() {
        assertEquals("Pedro", dummyUser.getName());
    }

    @Test
    void getSurname() {
        assertEquals("Perez", dummyUser.getSurname());
    }

    @Test
    void getPassword() {
        assertEquals("password", dummyUser.getPassword());
    }

    @Test
    void getRoles() {
        Collection<Role> collectionOfRoles = dummyUser.getRoles();
        assertEquals("USER", collectionOfRoles.stream().findFirst().get().getName());

    }


    @Test
    void setName() {
        String name = "Potato";
        dummyUser.setName(name);
        assertEquals(name, dummyUser.getName());
    }

    @Test
    void setSurname() {
        String surName = "Onion";
        dummyUser.setSurname(surName);
        assertEquals(surName, dummyUser.getSurname());
    }

    @Test
    void setPassword() {
        String password = "1234567890";
        dummyUser.setPassword(password);
        assertEquals(password, dummyUser.getPassword());
    }

    @Test
    void setRoles() {
        Role newRole = new Role( "ADMIN");
        Set<Role> setOfRoles = dummyUser.getRoles();
        setOfRoles.add(newRole);
        dummyUser.setRoles(setOfRoles);
        assertTrue(dummyUser.getRoles().contains(newRole));
        assertEquals(2, dummyUser.getRoles().size());
    }

    @Test
    void addMultipleRoles() {
        Role userRole = new Role( "USER");
        Role adminRole = new Role ( "ADMIN");
        Role sysAdminRole = new Role ( "SYSADMIN");
        Set<Role> newSetCollection = new HashSet<>();
        newSetCollection.addAll(Arrays.asList(userRole, adminRole,sysAdminRole));
        dummyUser.setRoles(newSetCollection);
        assertTrue(dummyUser.getRoles().contains(adminRole));
        assertTrue(dummyUser.getRoles().contains(userRole));
        assertTrue(dummyUser.getRoles().contains(sysAdminRole));
        assertFalse(dummyUser.getRoles().contains(new Role( "potato")));
    }


    @Test
    void getEmail() {
        assertEquals("pedro.perez@gmail.com", dummyUser.getEmail());
    }

    @Test
    void setEmail() {
        String newEmail = "pedro.parra@gmail.com";
        dummyUser.setEmail(newEmail);
        assertEquals(newEmail, dummyUser.getEmail());
    }

    @Test
    void testEquals() {
        Role newRole = new Role( "USER");
        Set<Role> newSetCollection = new HashSet<>();
        newSetCollection.add(newRole);
        User newUser = new User( "Pedro", "Perez", "pedro.perez@gmail.com", "password", newSetCollection );
        assertEquals(dummyUser, newUser);
    }

    @Test
    void testHashCode() {
        Role newRole = new Role( "USER");
        Set<Role> newSetCollection = new HashSet<>();
        newSetCollection.add(newRole);
        User newUser = new User( "Pedro", "Perez", "pedro.perez@gmail.com", "password", newSetCollection );
        assertEquals(dummyUser.hashCode(), newUser.hashCode());
    }

    @Test
    void testToString() {
        Role newRole = new Role( "USER");
        Set<Role> newSetCollection = new HashSet<>();
        newSetCollection.add(newRole);
        User newUser = new User("Pedro", "Perez", "pedro.perez@gmail.com", "password", newSetCollection );
        assertEquals(dummyUser.toString(), newUser.toString());
    }
}