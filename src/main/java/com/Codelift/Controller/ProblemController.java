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

    @GetMapping("/all")
    public ResponseEntity<?> getAllProblems(){
        return new ResponseEntity<>(problemService.getAllProblems(),HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<?> addProblem(@RequestBody ProblemDto problemDto){

        return new ResponseEntity<>(problemService.addProblem(problemDto), HttpStatus.ACCEPTED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleProblems(@PathVariable Long id){
        return new ResponseEntity<>(problemService.getSingleProblem(id),HttpStatus.OK);
    }

    @GetMapping("/instructor/{insId}")
    public ResponseEntity<?> getInstructorProblem(@PathVariable long insId){
        return new ResponseEntity<>(problemService.getInstructorsProblem(insId),HttpStatus.OK);
    }

    //check that oonly he can delete his ques
    @DeleteMapping("/{id}")
    public void Delete(@PathVariable Long id){
        problemService.deleteById(id);

    }

}
