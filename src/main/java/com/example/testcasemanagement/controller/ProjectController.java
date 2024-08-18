package com.example.testcasemanagement.controller;

import com.example.testcasemanagement.dto.ProjectDTO;
import com.example.testcasemanagement.model.Project;
import com.example.testcasemanagement.service.ProjectService;
import com.example.testcasemanagement.util.ApiResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ApiResponse<Project>> createProject(@RequestBody ProjectDTO project) {
        try{
            Project createdProject = projectService.createProject(project);
            return ResponseEntity.ok(new ApiResponse<>(createdProject, "created successfully"));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Project>>> getAllActiveProject() {
        try {
            List<Project> allActiveProjects = projectService.getAllActiveProjects();
            return ResponseEntity.ok(new ApiResponse<>(allActiveProjects, "found"));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, e.getMessage()));
        }
    }
}
