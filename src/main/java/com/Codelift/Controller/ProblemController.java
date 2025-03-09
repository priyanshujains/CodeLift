package com.Codelift.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    @GetMapping
    public ResponseEntity<?> getAllProblems(){
        return null;
    }


    @PostMapping
    public ResponseEntity<?> addProblem(){
        return null;
    }

}
