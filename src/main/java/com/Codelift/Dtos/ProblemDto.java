package com.Codelift.Dtos;



import com.Codelift.Models.DifficultyLevel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    public String getExampleInput() {
        return exampleInput;
    }

    public Long getId() {
        return id;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public String getExampleOutput() {
        return exampleOutput;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<String> getCompanies() {
        return companies;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<TestCaseDto> getTestCases() {
        return testCases;
    }

    public List<CodeTemplateDto> getCodeTemplates() {
        return codeTemplates;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficulty(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    public void setExampleInput(String exampleInput) {
        this.exampleInput = exampleInput;
    }

    public void setExampleOutput(String exampleOutput) {
        this.exampleOutput = exampleOutput;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public void setCompanies(List<String> companies) {
        this.companies = companies;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setTestCases(List<TestCaseDto> testCases) {
        this.testCases = testCases;
    }

    public void setCodeTemplates(List<CodeTemplateDto> codeTemplates) {
        this.codeTemplates = codeTemplates;
    }
}

