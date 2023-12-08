package com.example.projectexpenses.serviceimpl;
import java.util.*;

import com.example.projectexpenses.dtos.request.UserDto;
import com.example.projectexpenses.dtos.response.UserResponse;
import com.example.projectexpenses.exception.ResourceNotFoundException;
import com.example.projectexpenses.mapper.UserMapper;
import com.example.projectexpenses.model.User;
import com.example.projectexpenses.repository.UserRepository;
import com.example.projectexpenses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public void addUser(UserDto userDto) {
        userRepository.save(mapper.dtoToModel(userDto));
    }

    @Override
    public void deleteUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("id not found"));
        userRepository.deleteById(id);

    }

    @Override
    public UserDto getUserById(int id) {
       return userRepository.findById(id)
               .map(mapper::modelToDto)
               .orElseThrow(()->new ResourceNotFoundException("Id not found"));
    }

    @Override
    public UserResponse getAllUsers() {
        List<UserDto> userDtoList =  userRepository.findAll()
                .stream()
                .map(mapper::modelToDto)
                .toList();
        return getUserResponse(userDtoList);

    }

    @Override
    public UserResponse getUserWithMaxSalary(double salary) {
        List<UserDto> userDtoList = userRepository.getUserBySalaryOrderBySalaryDesc(salary)
                .stream()
                .map(mapper::modelToDto)
                .collect(Collectors.toList());
        return getUserResponse(userDtoList);
    }

    @Override
    public UserResponse getUserWithMaxExpenses() {
        return null;
    }

    public static UserResponse getUserResponse(List<UserDto> userDtoList){
         return UserResponse.builder()
                 .users(userDtoList)
                 .build();

    }
}
