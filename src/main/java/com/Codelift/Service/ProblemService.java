package com.Codelift.Service;


import com.Codelift.Dtos.ProblemDto;
import com.Codelift.Models.Problem;
import com.Codelift.Repos.CompanyRepo;
import com.Codelift.Repos.ProblemRepo;
import com.Codelift.Repos.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    }
}
