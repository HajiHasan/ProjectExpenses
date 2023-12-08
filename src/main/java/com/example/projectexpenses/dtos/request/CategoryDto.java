package com.example.projectexpenses.dtos.request;

import com.example.projectexpenses.model.Expenses;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {


    private String category_name;



}
