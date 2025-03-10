package com.Codelift.Controller;

import com.Codelift.Dtos.ProblemDto;
import com.Codelift.Service.ProblemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    private ProblemService problemService;

    public ProblemController(ProblemService problemService){
        this.problemService=problemService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProblems(){
        return null;
    }


    @PostMapping
    public ResponseEntity<?> addProblem(@RequestBody ProblemDto problemDto){

        return new ResponseEntity<>(problemService.addProblem(problemDto), HttpStatus.ACCEPTED);
    }

}
