package com.ssm.systemmeetmanagement.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    static Role dummyRole;
    @BeforeEach
    void setUp() {
        dummyRole = new Role(1L, "USER");
    }

    @Test
    void getId() {
        assertEquals(1L, dummyRole.getId());
;    }

    @Test
    void getName() {
        assertEquals("USER", dummyRole.getName());
    }

    @Test
    void setId() {
        dummyRole.setId(2L);
        assertEquals(2L, dummyRole.getId());
    }

    @Test
    void setName() {
        String sysAdmin = "SYSADMIN";
        dummyRole.setName(sysAdmin);
        assertEquals(sysAdmin, dummyRole.getName());
    }

    @Test
    void testEquals() {
        assertEquals(dummyRole, dummyRole);

        assertNotEquals(null, dummyRole);

        assertNotEquals("notArole", dummyRole);

        Role newRole = new Role(1L, "USER");
        assertEquals(dummyRole, newRole);
    }

    @Test
    void testHashCode() {
        Role newRole = new Role (1L, "USER");
        assertEquals(dummyRole.hashCode(), newRole.hashCode());
    }

    @Test
    void testHashCodeDifferentValuesSameHashCode() {
        Role role1 = new Role(1L, "USER");
        Role role2 = new Role(1L, "ADMIN");
        assertNotEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    void testEqualsDifferentObjects() {
        Role role1 = new Role(1L, "USER");
        Role role2 = new Role(2L, "ADMIN");
        assertNotEquals(role1, role2);
    }



    @Test
    void testToString() {
        Role newRole = new Role (1L, "USER");
        assertEquals(dummyRole.toString(), newRole.toString());
    }
}