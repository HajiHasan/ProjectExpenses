package com.example.projectexpenses.repository;

import com.example.projectexpenses.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> getUserBySalaryOrderBySalaryDesc(double salary);

}
