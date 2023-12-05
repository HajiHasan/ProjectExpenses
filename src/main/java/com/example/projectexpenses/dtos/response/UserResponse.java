package com.example.projectexpenses.dtos.response;

import com.example.projectexpenses.dtos.request.UserDto;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private List<UserDto> users;
}
