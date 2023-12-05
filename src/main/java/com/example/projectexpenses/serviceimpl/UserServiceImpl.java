package com.example.projectexpenses.serviceimpl;

import com.example.projectexpenses.dtos.request.UserDto;
import com.example.projectexpenses.dtos.response.UserResponse;
import com.example.projectexpenses.exception.ResourceNotFoundException;
import com.example.projectexpenses.model.User;
import com.example.projectexpenses.repository.UserRepository;
import com.example.projectexpenses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public void addUser(UserDto userDto) {
      User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userRepository.save(user);
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
               .map(this::convertToDto)
               .orElseThrow(()->new ResourceNotFoundException("Id not found"));
    }

    @Override
    public UserResponse getAllUsers() {
        List<UserDto> userDtoList =  userRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
        return getUserResponse(userDtoList);

    }

    @Override
    public UserResponse getUserWithMaxSalary(double salary) {
        List<UserDto> userDtoList = userRepository.getUserBySalaryOrderBySalaryDesc(salary)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return getUserResponse(userDtoList);
    }

    @Override
    public UserResponse getUserWithMaxExpenses() {
        return null;
    }
    private UserDto convertToDto(User user){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;

    }
    private UserResponse getUserResponse(List<UserDto> userDtoList){
         return UserResponse.builder()
                 .users(userDtoList)
                 .build();

    }
}
