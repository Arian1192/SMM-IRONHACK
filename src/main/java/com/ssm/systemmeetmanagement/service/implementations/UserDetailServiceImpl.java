package com.ssm.systemmeetmanagement.service.implementations;

import com.ssm.systemmeetmanagement.model.User;
import com.ssm.systemmeetmanagement.repository.UserRepository;
import com.ssm.systemmeetmanagement.service.dto.UserDto;
import com.ssm.systemmeetmanagement.utils.abstractConverter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
            List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
            UserConverter converter = new UserConverter();
            UserDto userDto = converter.fromEntity(user);
            userDto.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName()))));
            userDto.getRoles().stream()
                    .flatMap(role -> role.getPermissionEntityDto().stream())
                    .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

            return new org.springframework.security.core.userdetails.User(userDto.getName(),
                    userDto.getPassword(),
                    userDto.isEnabled(),
                    userDto.isAccountNoExpired(),
                    userDto.isCredentialNoExpired(),
                    userDto.isAccountNoLocked(),
                    authorityList);
        }
    }

