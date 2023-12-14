package com.example.projectexpenses.controller;

import com.example.projectexpenses.dtos.request.UserDto;
import com.example.projectexpenses.dtos.response.UserResponse;
import com.example.projectexpenses.serviceimpl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/adduser")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
    }
    @PostMapping("/login")
    public void loginUser(@RequestBody UserDto userDto){
        userService.loginUser(userDto);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable int id){
       return userService.getUserById(id);
    }
    @GetMapping("/users")
    public UserResponse getAllUsers(){
        return userService.getAllUsers();
    }
}
