package com.ssm.systemmeetmanagement.service.implementations;

import com.ssm.systemmeetmanagement.controller.auth.AuthResponse;
import com.ssm.systemmeetmanagement.controller.auth.LoginRequest;
import com.ssm.systemmeetmanagement.controller.auth.RegisterRequest;
import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.model.User;
import com.ssm.systemmeetmanagement.repository.RoleRepository;
import com.ssm.systemmeetmanagement.repository.UserRepository;
import com.ssm.systemmeetmanagement.service.interfaces.IAuthService;
import com.ssm.systemmeetmanagement.service.interfaces.IJwtService;
import com.ssm.systemmeetmanagement.utils.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImplementation implements IAuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final IJwtService jwtService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder().token(token).build();
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        Role role = roleRepository.findByName("USER").orElseThrow();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        String password = Utilities.generateRandomPassword();
        User user = User.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(password))
                .email(request.getEmail())
                .surname(request.getSurname())
                .roles(roles)
                .build();
        log.info(password);
        userRepository.save(user);
        return AuthResponse.builder().token(jwtService.getToken(user)).build();
    }
}
