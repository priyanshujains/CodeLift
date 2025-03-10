package com.Codelift.Repos;

import com.Codelift.Models.CodeTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CodeTemplateRepo extends JpaRepository<CodeTemplate,Long> {
}
