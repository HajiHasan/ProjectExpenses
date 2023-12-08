package com.example.projectexpenses.mapper;

import com.example.projectexpenses.dtos.request.ExpensesDto;
import com.example.projectexpenses.dtos.response.ExpensesResponse;
import com.example.projectexpenses.dtos.response.UserResponse;
import com.example.projectexpenses.model.Expenses;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ExpensesMapper {
    ExpensesDto modelToDto(Expenses expenses);

}
