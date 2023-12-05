package com.example.projectexpenses.dtos.response;

import com.example.projectexpenses.dtos.request.CategoryDto;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private List<CategoryDto> categories;
}
