package com.Codelift.Controller;




//package com.example.codecompiler.controller;

import com.Codelift.Dtos.CodeRequest;
import org.springframework.web.bind.annotation.*;
import java.io.*;

@RestController
@RequestMapping("/compile")
public class CodeExecutionController {


    @GetMapping
    public String hello(){
        return "hello";
    }

    @PostMapping
    public String executeCode(@RequestBody CodeRequest request) {
        String language =request.getLanguage();
        String code = request.getCode();

        try {
            String containerId = getDockerImage(language);
            return executeInDocker(containerId, code, language);
        } catch (Exception e) {
            return "Error executing code: " + e.getMessage();
        }
    }

    private String getDockerImage(String language) {
        switch (language) {
            case "python":
                return "python:3.9";
            case "java":
                return "openjdk:17";
            case "cpp":
                return "gcc:latest";
            default:
                throw new IllegalArgumentException("Unsupported language: " + language);
        }
    }

    private String executeInDocker(String image, String code, String language) throws IOException, InterruptedException {
        // 1️⃣ Create a temp file in a directory
        File tempDir = new File(System.getProperty("java.io.tmpdir"), "docker_code");
        if (!tempDir.exists()) {
            tempDir.mkdirs();  // Create directory if it doesn’t exist
        }

        File tempFile = new File(tempDir, "Code" + getFileExtension(language));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(code);
        }

        // 2️⃣ Ensure correct volume mounting
        ProcessBuilder builder = new ProcessBuilder(
                "docker", "run", "--rm",
                "-v", tempDir.getAbsolutePath() + ":/app",  // Mount the temp directory
                image, "sh", "-c", getCommand(language)   // Run shell command
        );

        System.out.println("Executing: " + builder.command());

        builder.redirectErrorStream(true);
        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        process.waitFor();

        // 3️⃣ Cleanup
        tempFile.delete();

        return output.toString();
    }


    private String getFileExtension(String language) {
        switch (language) {
            case "python": return ".py";
            case "java": return ".java";
            case "cpp": return ".cpp";
            default: return "";
        }
    }

    //    private String getCommand(String language) {
//        switch (language) {
//            case "python": return "python /app/code.py";
//            case "java": return "/bin/sh -c \\\"javac /app/code.java && java -cp /app Code\\\"";
//            case "cpp": return "g++ /app/code.cpp -o /app/code && /app/code";
//            default: return "";
//        }
//    }
    private String getCommand(String language) {
        switch (language) {
            case "python":
                return "python /app/" + "code.py";   // Ensure correct path
            case "java":
                return "sh -c 'javac /app/Code.java && java -cp /app Code'";
            case "cpp":
                return "sh -c 'g++ /app/code.cpp -o /app/code && /app/code'";
            default:
                return "";
        }
    }


}

