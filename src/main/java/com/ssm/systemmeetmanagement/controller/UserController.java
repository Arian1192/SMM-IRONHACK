package com.ssm.systemmeetmanagement.controller;
import com.ssm.systemmeetmanagement.model.Attendee;
import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.model.User;
import com.ssm.systemmeetmanagement.repository.RoleRepository;
import com.ssm.systemmeetmanagement.service.dto.AttendeeDto;
import com.ssm.systemmeetmanagement.service.dto.UserDto;
import com.ssm.systemmeetmanagement.service.implementations.AttendeeServiceImplementation;
import com.ssm.systemmeetmanagement.service.implementations.UserServiceImplementation;
import com.ssm.systemmeetmanagement.utils.Utilities;
import com.ssm.systemmeetmanagement.utils.abstractConverter.AttendeeConverter;
import com.ssm.systemmeetmanagement.utils.abstractConverter.UserConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImplementation userService;

    @Autowired
    private AttendeeServiceImplementation attendeeService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @GetMapping("/users/get_allUsers")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
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

    @GetMapping("/users/get_userById/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<?> getUserById(@PathVariable long id){
        Optional<User> maybeUser = userService.findUserById(id);
        if(maybeUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            User user = maybeUser.get();
            if(user instanceof Attendee){
                AttendeeDto attendeeDto = new AttendeeConverter().fromEntity((Attendee) user);
                return ResponseEntity.ok(attendeeDto);
            }else{
                UserDto userDto = new UserConverter().fromEntity(user);
                return ResponseEntity.ok(userDto);
            }
        }
    }

    @PutMapping("/users/put_userById/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<?> putUserById(@PathVariable long id, @RequestBody User userToUpdate) throws IllegalAccessException {
        Optional<User> maybeUser = userService.findUserById(id);
        if(maybeUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            User user = maybeUser.get();
            Class<?> userClass = user.getClass();
            for(Field field : userClass.getDeclaredFields()) {
                if (!field.getName().equals("id") && !field.getName().equals("roles")) {
                    field.setAccessible(true);
                    Object value = field.get(userToUpdate);
                    field.set(user, value);
                    field.setAccessible(false);
                }
            }
            userService.save(user);
            UserDto userDto = new UserConverter().fromEntity(user);
            return ResponseEntity.ok(userDto);
        }
    }


    @PostMapping("/users/post_newUser")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<?> createNewUser(@RequestBody User bodyUser){
        UserDto userDto;
        Optional<User> foundUser = userService.findUserByEmail(bodyUser.getEmail());
        if(foundUser.isPresent()){
            return ResponseEntity.badRequest().body("User found");
        }else{
            Role role = roleRepository.findByName("USER").orElseThrow();
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            String password = Utilities.generateRandomPassword();
            User user = User.builder()
                    .name(bodyUser.getName())
                    .password(passwordEncoder.encode(password))
                    .email(bodyUser.getEmail())
                    .surname(bodyUser.getSurname())
                    .roles(roles)
                    .build();
            userService.save(user);
            userDto = new UserConverter().fromEntity(user);
            return ResponseEntity.ok(userDto);
        }
    }

    @DeleteMapping("/users/delete_userById/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<String> deleteUserById(@PathVariable long id){
        Optional<User> maybeUser = userService.findUserById(id);
        if(maybeUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            User user = maybeUser.get();
            if(user instanceof Attendee){
                attendeeService.deleteById(id);
            }else{
                userService.deleteById(id);
            }
        }
       return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully");
    }
}




