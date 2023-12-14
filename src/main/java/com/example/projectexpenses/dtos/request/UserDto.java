package com.example.projectexpenses.dtos.request;

import com.example.projectexpenses.model.Expenses;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String name;

    private String surname;
    private String email;
    private String password;
  //
  //  private Boolean isActive;
    private double salary;

}
