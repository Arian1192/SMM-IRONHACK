package com.ssm.systemmeetmanagement.security;


import com.ssm.systemmeetmanagement.security.filters.JwtAuthenticationFilter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final String sysAdmin = "SYSADMIN";
    private final String admin = "ADMIN";
    private final String user = "USER";

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(authRequest ->
                            authRequest
                                    .requestMatchers(AUTH_WHITE_LIST).permitAll()
                                    .requestMatchers("/api/auth/register").hasAuthority(sysAdmin)
                                    .requestMatchers("/api/appointment/new_appointment").hasAnyAuthority(sysAdmin, admin)
                                    .requestMatchers("/api/appointment/get_appointment/**").authenticated()
                                    .requestMatchers("/api/appointment/get_allAppointments").hasAuthority(sysAdmin)
                                    .requestMatchers("/api/appointment/modify_appointment/**").hasAnyAuthority(sysAdmin, admin, user)
                                    .requestMatchers("/api/appointment/delete_appointment/**").hasAuthority(sysAdmin)
                                    .requestMatchers("/api/auth/login").permitAll()
                                    .requestMatchers("/api/users/get_AllUsers").permitAll()
                                    .anyRequest().authenticated()
                    )
                    .sessionManagement(sessionManager ->
                            sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    ).authenticationProvider(authProvider)
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "bearer-key",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")));
    }

    private static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**"
    };

}
