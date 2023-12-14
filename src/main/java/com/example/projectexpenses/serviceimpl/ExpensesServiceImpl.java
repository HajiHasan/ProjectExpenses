package com.example.projectexpenses.serviceimpl;

import com.example.projectexpenses.dtos.request.ExpensesDto;
import com.example.projectexpenses.dtos.response.ExpensesResponse;
import com.example.projectexpenses.mapper.ExpensesMapper;
import com.example.projectexpenses.model.Expenses;
import com.example.projectexpenses.model.User;
import com.example.projectexpenses.repository.ExpensesRepository;
import com.example.projectexpenses.service.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {
    private final ExpensesRepository expensesRepository;
    private final ExpensesMapper mapper;
    @Override
    public void addExpenses(ExpensesDto expensesDto) {
        expensesRepository.save(mapper.dtoToModel(expensesDto));

    }

    @Override
    public ExpensesResponse getAllExpenses() {
        List<ExpensesDto> expensesDtoList = expensesRepository.findAll()
                .stream()
                .map(mapper::modelToDto)
                .toList();
        return ExpensesResponse.builder()
                .expenses(expensesDtoList)
                .build();

    }

    @Override
    public ExpensesResponse getExpensesByCategoryId(long category_id) {
         List<ExpensesDto> expensesDtoList = expensesRepository.getExpensesByCategory_Id(category_id)
                 .stream()
                 .map(mapper::modelToDto)
                 .toList();
         return ExpensesResponse.builder()
                 .expenses(expensesDtoList)
                 .build();
    }


}
