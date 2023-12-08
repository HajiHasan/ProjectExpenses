package com.example.projectexpenses.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String category_name;
    //deyisilik nomre -- 2
    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Expenses> expenses;

}
