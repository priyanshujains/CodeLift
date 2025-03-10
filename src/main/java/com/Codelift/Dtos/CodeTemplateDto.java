package com.Codelift.Dtos;

import lombok.Data;

@Data
public class CodeTemplateDto {
    private Long id;
    private String language;    // e.g., "JAVA", "PYTHON", etc.
    private String templateCode;
}
