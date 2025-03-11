package com.Codelift.Dtos;

import com.Codelift.Models.DifficultyLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemTitleDto {

    private String title;
    private long id;
    private DifficultyLevel difficultyLevel;
    private boolean visibility;
}
