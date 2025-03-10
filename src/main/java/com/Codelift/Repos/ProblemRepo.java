package com.Codelift.Repos;

import com.Codelift.Models.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepo extends JpaRepository<Problem,Long> {
}
