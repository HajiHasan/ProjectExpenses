package com.example.projectexpenses.model;

import com.example.projectexpenses.dtos.request.ExpensesDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 25)
    private String name;

    @Column(length = 30)
    private String surname;

    private double salary;

    private String email;

    private String password;

//    @Column(name = "is_active")
//    private boolean isActive = true;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Expenses> expenses;

}
