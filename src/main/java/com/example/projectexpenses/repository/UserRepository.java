package com.example.projectexpenses.repository;

import com.example.projectexpenses.dtos.request.UserDto;
import com.example.projectexpenses.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> getUserBySalaryOrderBySalaryDesc(double salary);

    Optional <User> findUserByEmail(String email);
}
