package com.example.projectexpenses.service;

import com.example.projectexpenses.dtos.request.UserDto;
import com.example.projectexpenses.dtos.response.UserResponse;
import com.example.projectexpenses.model.User;

public interface UserService {
    void registerUser(UserDto userDto);

    void loginUser(UserDto userDto);
    void deleteUser(int id);
    UserDto getUserById(int id);
    UserResponse getAllUsers();
    UserResponse getUserWithMaxSalary(double salary);
    UserResponse getUserWithMaxExpenses();

    //UserDto getUserByEmail(String email);

}
