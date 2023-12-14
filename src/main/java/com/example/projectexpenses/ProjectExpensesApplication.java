package com.example.projectexpenses;

import com.example.projectexpenses.model.User;
import com.example.projectexpenses.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@RequiredArgsConstructor
public class ProjectExpensesApplication {

    private final UserRepository userRepository;



    public static void main(String[] args) {
        SpringApplication.run(ProjectExpensesApplication.class, args);
    }

}
