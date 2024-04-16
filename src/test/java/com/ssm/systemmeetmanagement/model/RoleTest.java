package com.ssm.systemmeetmanagement.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    static Role dummyRole;
    @BeforeEach
    void setUp() {
        dummyRole = new Role( "USER");
    }


    @Test
    void getName() {
        assertEquals("USER", dummyRole.getName());
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

        Role newRole = new Role( "USER");
        assertEquals(dummyRole, newRole);
    }

    @Test
    void testHashCode() {
        Role newRole = new Role ( "USER");
        assertEquals(dummyRole.hashCode(), newRole.hashCode());
    }

    @Test
    void testHashCodeDifferentValuesSameHashCode() {
        Role role1 = new Role( "USER");
        Role role2 = new Role( "ADMIN");
        assertNotEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    void testEqualsDifferentObjects() {
        Role role1 = new Role( "USER");
        Role role2 = new Role( "ADMIN");
        assertNotEquals(role1, role2);
    }



    @Test
    void testToString() {
        Role newRole = new Role ( "USER");
        assertEquals(dummyRole.toString(), newRole.toString());
    }
}