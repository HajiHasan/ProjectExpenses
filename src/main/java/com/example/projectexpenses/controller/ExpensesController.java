package com.example.projectexpenses.controller;

import com.example.projectexpenses.dtos.response.ExpensesResponse;
import com.example.projectexpenses.model.Expenses;
import com.example.projectexpenses.serviceimpl.ExpensesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exp")
public class ExpensesController {
    private final ExpensesServiceImpl expensesService;

    @GetMapping("/allexpenses")
    @ResponseStatus(HttpStatus.OK)
    public ExpensesResponse getAllExpenses(){
        return expensesService.getAllExpenses();
    }
}
