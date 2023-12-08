package com.example.projectexpenses.mapper;

import com.example.projectexpenses.dtos.request.UserDto;
import com.example.projectexpenses.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
   UserDto modelToDto(User user);
   User dtoToModel(UserDto userDto);

}
