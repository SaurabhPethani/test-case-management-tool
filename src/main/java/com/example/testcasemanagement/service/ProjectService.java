package com.example.testcasemanagement.service;

import com.example.testcasemanagement.dto.ProjectDTO;
import com.example.testcasemanagement.model.Project;
import com.example.testcasemanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setActive(true);
        project.setJiraTicketId(projectDTO.getJiraTicketId());
        return projectRepository.save(project);
    }

    public List<Project> getAllActiveProjects() {
        return projectRepository.findByActive(true);
    }
}
