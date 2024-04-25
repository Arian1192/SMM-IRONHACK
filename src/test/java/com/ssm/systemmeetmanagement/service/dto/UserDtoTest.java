package com.ssm.systemmeetmanagement.service.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

class UserDtoTest {

    private UserDto dto;

    @BeforeEach
    public void setUp() {
        dto = new UserDto();
    }

    @Test
    void getName() {
        dto.setName("Arian");
        assertEquals("Arian", dto.getName());
    }

    @Test
    void setName() {
        dto.setName("John");
        assertEquals("John", dto.getName());
    }

    @Test
    void getSurname() {
        dto.setSurname("Doe");
        assertEquals("Doe", dto.getSurname());
    }

    @Test
    void getEmail() {
        dto.setEmail("example@example.com");
        assertEquals("example@example.com", dto.getEmail());
    }

    @Test
    void getPassword() {
        dto.setPassword("password123");
        assertEquals("password123", dto.getPassword());
    }

    @Test
    void getRoles() {
        RoleDto roledto = new RoleDto();
        Set<RoleDto> roles = new HashSet<>();
        roles.add(roledto);
        dto.setRoles(roles);
        assertEquals(1, dto.getRoles().size());
    }

    @Test
    void isEnabled() {
        dto.setEnabled(true);
        assertEquals(true, dto.isEnabled());
    }

    @Test
    void isAccountNonExpired() {
        dto.setAccountNoExpired(true);
        assertEquals(true, dto.isAccountNoExpired());
    }

    @Test
    void isCredentialsNonExpired() {
        dto.setCredentialNoExpired(true);
        assertEquals(true, dto.isCredentialNoExpired());
    }

    @Test
    void isAccountNonLocked() {
        dto.setAccountNoLocked(true);
        assertEquals(true, dto.isAccountNoLocked());
    }

    @Test
    void setSurname() {
        dto.setSurname("Smith");
        assertEquals("Smith", dto.getSurname());
    }

    @Test
    void setEmail() {
        dto.setEmail("test@test.com");
        assertEquals("test@test.com", dto.getEmail());
    }

    @Test
    void setPassword() {
        dto.setPassword("newPassword");
        assertEquals("newPassword", dto.getPassword());
    }

    @Test
    void setRoles() {
        RoleDto roleDtoto = new RoleDto();
        Set<RoleDto> roles = new HashSet<>();
        roles.add(roleDtoto);
        dto.setRoles(roles);
        assertEquals(1, dto.getRoles().size());
    }

    @Test
    void setEnabled() {
        dto.setEnabled(false);
        assertEquals(false, dto.isEnabled());
    }

    @Test
    void setAccountNonExpired() {
        dto.setAccountNoExpired(false);
        assertEquals(false, dto.isCredentialNoExpired());
    }

    @Test
    void setCredentialsNonExpired() {
        dto.setCredentialNoExpired(false);
        assertEquals(false, dto.isCredentialNoExpired());
    }

    @Test
    void setAccountNonLocked() {
        dto.setAccountNoLocked(false);
        assertEquals(false, dto.isAccountNoLocked());
    }
}
