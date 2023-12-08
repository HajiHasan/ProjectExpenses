package com.example.projectexpenses.service;

import com.example.projectexpenses.dtos.response.ExpensesResponse;

import java.util.List;

public interface ExpensesService {
    void addExpenses();
    ExpensesResponse getAllExpenses();



}
