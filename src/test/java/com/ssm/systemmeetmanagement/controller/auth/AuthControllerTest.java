package com.ssm.systemmeetmanagement.controller.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.systemmeetmanagement.model.PermissionEntity;
import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.model.User;
import com.ssm.systemmeetmanagement.repository.PermissionRepository;
import com.ssm.systemmeetmanagement.repository.RoleRepository;
import com.ssm.systemmeetmanagement.repository.UserRepository;
import com.ssm.systemmeetmanagement.service.dto.EmailAndPasswordDto;
import com.ssm.systemmeetmanagement.service.dto.RegisterUserDto;
import com.ssm.systemmeetmanagement.service.dto.RoleDto;
import com.ssm.systemmeetmanagement.utils.abstractConverter.RoleConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AuthControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        /* CREATE PERMISSIONS */
        PermissionEntity permissionCreate = new PermissionEntity("CREATE");
        PermissionEntity permissionRead = new PermissionEntity("READ");
        PermissionEntity permissionUpdate = new PermissionEntity("UPDATE");
        PermissionEntity permissionDelete = new PermissionEntity("DELETE");
        permissionRepository.saveAll(Arrays.asList(permissionCreate, permissionRead, permissionUpdate, permissionDelete));

        /* CREATE ROLES */
        Role sysAdminRole = new Role("SYSADMIN");
        sysAdminRole.setPermissionEntityList(Set.of(permissionCreate, permissionRead, permissionUpdate, permissionDelete));
        Role adminRole = new Role("ADMIN");
        adminRole.setPermissionEntityList(Set.of(permissionRead, permissionCreate, permissionUpdate));
        Role userRole = new Role("USER");
        userRole.setPermissionEntityList(Set.of(permissionRead, permissionUpdate));
        roleRepository.saveAll(Arrays.asList(sysAdminRole, adminRole, userRole));

        Set<Role> roles = new HashSet<>();
        roles.add(sysAdminRole);

        /* CREATE SYSADMIN */
        User sysAdminUser = new User("Arian",
                "Collaso",
                "arian.collaso.rodriguez@gmail.com",
                passwordEncoder.encode("40S4r3dder"),
                roles
        );

        userRepository.save(sysAdminUser);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        permissionRepository.deleteAll();
    }


    public MvcResult createSysAdmin() throws Exception {
        EmailAndPasswordDto emailAndPasswordDto = new EmailAndPasswordDto("arian.collaso.rodriguez@gmail.com", "40S4r3dder");
        String body = new ObjectMapper().writeValueAsString(emailAndPasswordDto);
        return mockMvc.perform(post("/api/auth/login")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isOk()).andReturn();
    }

    @Test
    void login() throws Exception {
        MvcResult mvcResult = createSysAdmin();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void register() throws Exception {
        MvcResult mvcResultAdmin = createSysAdmin();
        String authToken = mvcResultAdmin.getResponse().getContentAsString();
        RoleDto role = new RoleConverter().fromEntity(roleRepository.findByName("USER").orElseThrow());
        Set<RoleDto> roles = new HashSet<>();
        roles.add(role);
        RegisterUserDto registerUserDto = new RegisterUserDto("Pedro", "Ramirez", "pedro.ramirez@gmail.com", roles);
        String bodyRegister = new ObjectMapper().writeValueAsString(registerUserDto);
        MvcResult mvcResult = mockMvc.perform(post("/api/auth/register")
                .content(bodyRegister)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }


}