package com.Codelift.Dtos;



import com.Codelift.Models.DifficultyLevel;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProblemDto {
    private Long id;
    private Long instructorId;
    private String title;
    private String description;
    private DifficultyLevel difficulty; // Enum: EASY, MEDIUM, HARD
    private String exampleInput;
    private String exampleOutput;
    private LocalDateTime createdAt;

    private boolean visibility;

    private String functionName;

    // Instead of company IDs, we store company names here
    private List<String> companies;
    private List<String> tags;
    private List<TestCaseDto> testCases;
    private List<CodeTemplateDto> codeTemplates;
}

