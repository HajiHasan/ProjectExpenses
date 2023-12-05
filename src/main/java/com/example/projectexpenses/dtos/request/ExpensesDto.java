package com.example.projectexpenses.dtos.request;

import lombok.Data;

@Data
public class ExpensesDto {
    private int id;
    private double amount;
    private int category_id;
    private int user_id;
}
