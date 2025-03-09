package com.Codelift.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tag {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Problem> problems;
}
