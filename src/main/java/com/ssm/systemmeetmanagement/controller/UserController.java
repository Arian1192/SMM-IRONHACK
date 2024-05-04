package com.ssm.systemmeetmanagement.controller;



import com.ssm.systemmeetmanagement.model.Attendee;
import com.ssm.systemmeetmanagement.model.User;
import com.ssm.systemmeetmanagement.service.dto.AttendeeDto;
import com.ssm.systemmeetmanagement.service.dto.UserDto;
import com.ssm.systemmeetmanagement.service.implementations.UserServiceImplementation;
import com.ssm.systemmeetmanagement.utils.abstractConverter.AttendeeConverter;
import com.ssm.systemmeetmanagement.utils.abstractConverter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImplementation userService;

    @GetMapping("/users/get_AllUsers")
    public ResponseEntity<List<?>> getAllUsers() {
        Optional<List<User>> maybeListOfUsers = userService.findAllUsers();
        List<Object> listOfUsers = new ArrayList<>();
        if (maybeListOfUsers.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            for(User user : maybeListOfUsers.get()){
                if(user instanceof Attendee){
                    AttendeeDto attendeeDto = new AttendeeConverter().fromEntity((Attendee) user);
                    listOfUsers.add(attendeeDto);
                }else{
                    UserDto userDto = new UserConverter().fromEntity(user);
                    listOfUsers.add(userDto);
                }
            }
            return ResponseEntity.ok((List<?>) listOfUsers);
        }
    }


}




