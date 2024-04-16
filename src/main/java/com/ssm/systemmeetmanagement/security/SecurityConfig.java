package com.ssm.systemmeetmanagement.security;


import com.ssm.systemmeetmanagement.service.implementations.UserDetailServiceImpl;
import com.ssm.systemmeetmanagement.service.implementations.UserServiceImplementation;
import com.ssm.systemmeetmanagement.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {


        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailsService){
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setPasswordEncoder(passwordEncoder());
            provider.setUserDetailsService(userDetailsService);
            return provider;
        }



        @Bean
        public PasswordEncoder passwordEncoder(){
            return NoOpPasswordEncoder.getInstance();
        }

//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//                return httpSecurity
//                        .csrf(csrf -> csrf.disable())
//                        .httpBasic(Customizer.withDefaults())
//                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                        .authorizeHttpRequests(http -> {
//                            // Setup public-endpoints
//                            http.requestMatchers(HttpMethod.GET, "/api/hello").permitAll();
//                            // Setup private-endpoints
//                            http.requestMatchers(HttpMethod.GET, "/api/auth/hello").hasAuthority("CREATE");
//                            // Setup rest of NON-SPECIFY end-points
//                            http.anyRequest().denyAll();
//                        })
//                        .build();
//        }

            @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                return httpSecurity
                        .csrf(csrf -> csrf.disable())
                        .httpBasic(Customizer.withDefaults())
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .build();
        }

}
