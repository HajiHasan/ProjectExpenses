package com.example.projectexpenses.dtos.response;

import com.example.projectexpenses.dtos.request.ExpensesDto;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesResponse {

    private List<ExpensesDto> expenses;
}
