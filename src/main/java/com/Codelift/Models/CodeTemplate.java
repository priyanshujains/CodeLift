package com.Codelift.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class CodeTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The language for which this template is applicable (e.g., JAVA, PYTHON, CPP)
    private String language;

    // The code template, which might include a main method or a main class definition
    @Column(columnDefinition = "TEXT")
    private String templateCode;

    // Many templates can be associated with one problem
    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;
}