package com.Codelift.Models;

import jakarta.persistence.*;

@Entity
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String input;
    private String expectedOutput;

    // Indicates if the test case is visible to the user
    private boolean visible; // true for example cases, false for hidden ones

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    // Getters and Setters
}

