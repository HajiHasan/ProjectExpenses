package com.example.projectexpenses.serviceimpl;

import com.example.projectexpenses.dtos.request.AuthenticationRequest;
import com.example.projectexpenses.dtos.request.UserDto;
import com.example.projectexpenses.dtos.response.UserResponse;
import com.example.projectexpenses.exception.ResourceNotFoundException;
import com.example.projectexpenses.mapper.UserMapper;
import com.example.projectexpenses.model.User;
import com.example.projectexpenses.repository.UserRepository;
import com.example.projectexpenses.service.JwtService;
import com.example.projectexpenses.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    //1.Filter yazmaq  2.User Register   3. Login  4. Controller
    @Override
    public void registerUser(UserDto userDto) {

            log.info("Registering user with username : {}", userDto.getEmail());
            User user = mapper.dtoToModel(userDto);
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setEmail(userDto.getEmail());
            user.setSalary(userDto.getSalary());
           userRepository.save(user);
    }

    @Override
    public void loginUser(UserDto userDto) {
        User user = userRepository.findUserByEmail(userDto.getEmail())
                .orElseThrow(()-> new ResourceNotFoundException("User is not registered"));
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();

        authenticationRequest.setEmail(userDto.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest
                , userDto.getPassword());

        authentication = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtService.createToken(authentication);



    }


    @Override
    public void deleteUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("id not found"));
        userRepository.delete(user);

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

//    @Override
//    public UserDto getUserByEmail(String email) {
//        User user =  userRepository.findUserByEmail(email)
//                .get();
//        UserDto userDto = mapper.modelToDto(user);
//        return userDto;
//
//    }

    public static UserResponse getUserResponse(List<UserDto> userDtoList){
         return UserResponse.builder()
                 .users(userDtoList)
                 .build();

    }
}
