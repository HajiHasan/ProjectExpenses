package com.example.projectexpenses.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private double salary;
}
