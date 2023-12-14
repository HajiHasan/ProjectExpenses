package com.example.projectexpenses.service;

import com.example.projectexpenses.dtos.request.ExpensesDto;
import com.example.projectexpenses.dtos.response.ExpensesResponse;

import java.util.List;

public interface ExpensesService {
    void addExpenses(ExpensesDto expensesDto);
    ExpensesResponse getAllExpenses();

    ExpensesResponse getExpensesByCategoryId(long category_id);



}
