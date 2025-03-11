package com.Codelift.Repos;

import com.Codelift.Models.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepo extends JpaRepository<Problem,Long> {

    List<Problem> findByVisibilityTrue();

    List<Problem> findByInstructorId(Long id);
}
