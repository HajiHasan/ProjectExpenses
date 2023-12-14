package com.example.projectexpenses.controller;

import com.example.projectexpenses.dtos.request.ExpensesDto;
import com.example.projectexpenses.dtos.response.ExpensesResponse;
import com.example.projectexpenses.model.Expenses;
import com.example.projectexpenses.serviceimpl.ExpensesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping("/user/{category_id}")
    public ExpensesResponse getExpensesByCategoryId(@PathVariable long category_id){
        return expensesService.getExpensesByCategoryId(category_id);
    }
    @PostMapping("/addexpenses")
    @ResponseStatus(HttpStatus.CREATED)
    public void addExpenses(@RequestBody ExpensesDto expensesDto){
        expensesService.addExpenses(expensesDto);
    }

}
