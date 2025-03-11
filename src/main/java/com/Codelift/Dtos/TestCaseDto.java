package com.Codelift.Dtos;


import lombok.Data;

@Data
public class TestCaseDto {
    private Long id;
    private String input;
    private String expectedOutput;
    private Boolean visible;
}
