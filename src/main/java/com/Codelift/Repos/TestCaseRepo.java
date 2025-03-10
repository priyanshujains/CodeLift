package com.Codelift.Repos;

import com.Codelift.Models.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepo extends JpaRepository<TestCase,Long> {
}
