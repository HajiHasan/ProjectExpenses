package com.example.projectexpenses.repository;

import com.example.projectexpenses.dtos.response.ExpensesResponse;
import com.example.projectexpenses.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Integer> {

}
