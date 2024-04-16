package com.ssm.systemmeetmanagement.controller;

import com.ssm.systemmeetmanagement.service.dto.UserDto;
import com.ssm.systemmeetmanagement.service.interfaces.IUserService;
import com.ssm.systemmeetmanagement.utils.abstractConverter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@PreAuthorize("denyAll()")
public class UserController {
    @Autowired
    private IUserService iUserService;
//    @PostMapping("/admin/register")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createNewUser(@RequestBody UserDto userDto){
//        iUserService.createNewUser(new UserConverter().fromDto(userDto));
//    }

    @GetMapping("/hello")
    @PreAuthorize("permitAll()")
    public String hello(){
        return "HOLA MUNDO";
    }

    @GetMapping("/auth/hello")
    @PreAuthorize("hasAuthority('CREATE')")
    public String securedHello(){
        return "HOLA MUNDO SEGURO";
    }
}
