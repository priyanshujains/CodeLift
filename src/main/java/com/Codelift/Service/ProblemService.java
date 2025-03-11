package com.Codelift.Service;


import com.Codelift.Dtos.*;
import com.Codelift.Models.*;
import com.Codelift.Repos.CompanyRepo;
import com.Codelift.Repos.ProblemRepo;
import com.Codelift.Repos.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.SpringObjenesis;
import org.springframework.stereotype.Service;
import com.Codelift.Dtos.ProblemDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProblemService {

   final private  ProblemRepo problemRepo;
   final private CompanyRepo companyRepo;
   final private TagRepo tagRepo;

    @Autowired
    public ProblemService(ProblemRepo problemRepo,
                          CompanyRepo companyRepo,
                          TagRepo tagRepo){
        this.problemRepo=problemRepo;
        this.tagRepo=  tagRepo;
        this.companyRepo=companyRepo;
    }


    public ProblemDto addProblem(ProblemDto problemDto) {


        Problem problem = new Problem();
        problem.setTitle(problemDto.getTitle());
        problem.setDescription(problemDto.getDescription());
        problem.setDifficulty(problemDto.getDifficulty());
        problem.setExampleInput(problemDto.getExampleInput());
        problem.setExampleOutput(problemDto.getExampleOutput());
        problem.setFunctionName(problemDto.getFunctionName());
        // Set creation timestamp (or use the default value in the entity)
        problem.setCreatedAt(LocalDateTime.now());
        problem.setInstructorId(problemDto.getInstructorId());
        problem.setVisibility(problemDto.isVisibility());


        // Map companies: Look up by company name and add them
        List<Company> companies = new ArrayList<>();
        if (problemDto.getCompanies() != null) {
            for (String companyName : problemDto.getCompanies()) {
                Company company = companyRepo.findByName(companyName);
                if (company == null) {
                    // Optionally create a new Company if not found
                    company = new Company();
                    company.setName(companyName.toUpperCase());
                    company = companyRepo.save(company);
                }
                companies.add(company);
            }
        }
        problem.setCompanies(companies);


        // Map test cases
        List<TestCase> testCases = new ArrayList<>();
        if (problemDto.getTestCases() != null) {
            for (TestCaseDto tcDto : problemDto.getTestCases()) {
                TestCase testCase = new TestCase();
                testCase.setInput(tcDto.getInput());
                testCase.setExpectedOutput(tcDto.getExpectedOutput());
                testCase.setProblem(problem);  // set the owning side of the relationship
                testCases.add(testCase);
            }
        }
        problem.setTestCases(testCases);



        // Map tags: Look up by tag name and add them
        List<Tag> tags = new ArrayList<>();
        if (problemDto.getTags() != null) {
            for (String tagName : problemDto.getTags()) {
                Tag tag = tagRepo.findByName(tagName);
                if (tag == null) {
                    // Optionally create a new Tag if not found
                    tag = new Tag();
                    tag.setName(tagName.toUpperCase());
                    tag = tagRepo.save(tag);
                }
                tags.add(tag);
            }
        }
        problem.setTags(tags);

        List<CodeTemplate> codeTemplates = new ArrayList<>();
        if (problemDto.getCodeTemplates() != null) {
            for (CodeTemplateDto ctDto : problemDto.getCodeTemplates()) {
                CodeTemplate codeTemplate = new CodeTemplate();
                codeTemplate.setLanguage(ctDto.getLanguage());
                codeTemplate.setTemplateCode(ctDto.getTemplateCode());
                codeTemplate.setProblem(problem);
                codeTemplates.add(codeTemplate);                        //due to cascade=All it will save automatically
            }
        }
        problem.setCodeTemplates(codeTemplates);



        Problem savedProblem = problemRepo.save(problem);

        return toDto(savedProblem);
    }




    public  static ProblemDto toDto(Problem problem) {
        ProblemDto dto = new ProblemDto();
        dto.setId(problem.getId());
        dto.setTitle(problem.getTitle());
        dto.setDescription(problem.getDescription());
        dto.setDifficulty(problem.getDifficulty());
        dto.setExampleInput(problem.getExampleInput());
        dto.setExampleOutput(problem.getExampleOutput());
        dto.setFunctionName(problem.getFunctionName());
        dto.setCreatedAt(problem.getCreatedAt());
        dto.setInstructorId(problem.getInstructorId());
        dto.setVisibility(problem.isVisibility());

        // Map tags to a list of tag names (assuming Tag has a getName() method)
        List<String> tagNames = (problem.getTags() !=null)
                ?problem.getTags()
                .stream()
                .map(Tag::getName)
                .collect(Collectors.toList()):new ArrayList<>();
        dto.setTags(tagNames);

        // Map companies to a list of company names (assuming Company has a getName() method)
        List<String> companyNames = problem.getCompanies()
                .stream()
                .map(Company::getName)
                .collect(Collectors.toList());
        dto.setCompanies(companyNames);

        // Map test cases to TestCaseDto
        List<TestCaseDto> testCaseDtos = problem.getTestCases()
                .stream()
                .map(tc -> {
                    TestCaseDto tcDto = new TestCaseDto();
                    tcDto.setId(tc.getId());
                    tcDto.setInput(tc.getInput());
                    tcDto.setExpectedOutput(tc.getExpectedOutput());
                    return tcDto;
                })
                .collect(Collectors.toList());
        dto.setTestCases(testCaseDtos);

        // Map code templates to CodeTemplateDto
        List<CodeTemplateDto> codeTemplateDtos = problem.getCodeTemplates()
                .stream()
                .map(ct -> {
                    CodeTemplateDto ctDto = new CodeTemplateDto();
                    ctDto.setId(ct.getId());
                    ctDto.setLanguage(ct.getLanguage());
                    ctDto.setTemplateCode(ct.getTemplateCode());
                    return ctDto;
                })
                .collect(Collectors.toList());
        dto.setCodeTemplates(codeTemplateDtos);

        return dto;
    }

    public void deleteById(Long id) {
        problemRepo.deleteById(id);
    }

    public List<ProblemTitleDto> getAllProblems() {
        List<Problem> problemList=problemRepo.findByVisibilityTrue();


        ArrayList<ProblemTitleDto> list=new ArrayList<>();
        for(Problem problem: problemList){
            ProblemTitleDto problemTitleDto=new ProblemTitleDto();
            problemTitleDto.setId(problem.getId());
            problemTitleDto.setTitle(problem.getTitle());
            problemTitleDto.setDifficultyLevel(problem.getDifficulty());
            problemTitleDto.setVisibility(problem.isVisibility());

            list.add(problemTitleDto);
        }

        return list;
    }


    public ProblemDto getSingleProblem(Long id){
        Optional<Problem> optionalProblem=problemRepo.findById(id);

        if(optionalProblem.isEmpty())throw new RuntimeException("Problem doesn't exist");
        return toDto(optionalProblem.get());
    }

    public List<ProblemTitleDto> getInstructorsProblem(long insId) {
        List<Problem>problemList=problemRepo.findByInstructorId(insId);

        List<ProblemTitleDto> problemTitleDtos=new ArrayList<>();

        for(Problem problem:problemList){
            ProblemTitleDto problemTitleDto=new ProblemTitleDto();
            problemTitleDto.setId(problem.getId());
            problemTitleDto.setDifficultyLevel(problem.getDifficulty());
            problemTitleDto.setTitle(problem.getTitle());
            problemTitleDto.setVisibility(problem.isVisibility());

            problemTitleDtos.add(problemTitleDto);
        }


        return problemTitleDtos;

    }
}
