package com.example.projectexpenses.dtos.request;

import com.example.projectexpenses.model.Category;
import com.example.projectexpenses.model.User;
import lombok.Data;

@Data
public class ExpensesDto {


    private double amount;

    private UserDto user;

    private CategoryDto category;
}
