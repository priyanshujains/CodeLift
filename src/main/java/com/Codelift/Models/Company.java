package com.Codelift.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // e.g., "Google", "Facebook", etc.

    @ManyToMany(mappedBy = "companies")
    private List<Problem> problems;

    // Getters and Setters
}
