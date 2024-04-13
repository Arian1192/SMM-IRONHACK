package model;

import enums.Rol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    static User dummyUser;

    @BeforeEach
    void setUp() {
        Set<Rol> roles = new HashSet<>();
        roles.add(Rol.USER);
        dummyUser = new User(1L, "Pedro", "Perez", "pedro.perez@gmail.com", "password", roles );

    }

    @Test
    void getId() {
        assertEquals(1L, dummyUser.getId());
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
        Rol rol = Rol.USER;
        Set<Rol> roleSet = dummyUser.getRoles();
        assertTrue(roleSet.contains(rol));
    }

    @Test
    void setId() {
        dummyUser.setId(2L);
        assertEquals(2L, dummyUser.getId());
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
        Rol newRole = Rol.ADMIN;
        Set<Rol> roles = dummyUser.getRoles();
        roles.add(newRole);
        assertEquals(2, roles.size());
        assertTrue(roles.contains(newRole));
    }

    @Test
    void addMultipleRoles() {

        Rol userRole = Rol.USER;
        Rol adminRole = Rol.ADMIN;
        Set<Rol> roles = new HashSet<>();
        roles.add(userRole);
        roles.add(adminRole);
        dummyUser.setRoles(roles);
        assertTrue(dummyUser.getRoles().contains(Rol.USER));
        assertTrue(dummyUser.getRoles().contains(Rol.ADMIN));
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
        Set<Rol> roles = new HashSet<>();
        roles.add(Rol.USER);
        User newUser = new User(1L, "Pedro", "Perez", "pedro.perez@gmail.com", "password", roles );
        assertEquals(newUser, dummyUser);
    }

    @Test
    void testHashCode() {
        Set<Rol> roles = new HashSet<>();
        roles.add(Rol.USER);
        User newUser = new User(1L, "Pedro", "Perez", "pedro.perez@gmail.com", "password", roles );
        assertEquals(newUser.hashCode(), dummyUser.hashCode());
    }

    @Test
    void testToString() {
        Set<Rol> roles = new HashSet<>();
        roles.add(Rol.USER);
        User newUser = new User(1L, "Pedro", "Perez", "pedro.perez@gmail.com", "password", roles );
        assertEquals(newUser.toString(), dummyUser.toString());
    }
}