package com.ssm.systemmeetmanagement.controller;

import com.ssm.systemmeetmanagement.service.dto.UserDto;
import com.ssm.systemmeetmanagement.service.interfaces.IUserService;
import com.ssm.systemmeetmanagement.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class UserController {
    @Autowired
    private IUserService iUserService;
    @PostMapping("/admin/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewUser(@RequestBody UserDto userDto){
        UserConverter converter = new UserConverter();
        iUserService.createNewUser(converter.fromDto(userDto));
    }
}
