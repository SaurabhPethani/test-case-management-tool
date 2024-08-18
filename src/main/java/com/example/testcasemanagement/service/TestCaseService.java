package com.example.testcasemanagement.service;

import com.example.testcasemanagement.dto.TestCaseDTO;
import com.example.testcasemanagement.model.Project;
import com.example.testcasemanagement.model.TestCase;
import com.example.testcasemanagement.repository.ProjectRepository;
import com.example.testcasemanagement.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public TestCase createTestCase(TestCaseDTO testCaseDTO) {
        Project project = projectRepository.findById(testCaseDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        TestCase testCase = new TestCase();
        testCase.setProject(project);
        testCase.setTestCaseId(testCaseDTO.getTestCaseId());
        testCase.setTestCaseName(testCaseDTO.getTestCaseName());
        testCase.setType(testCaseDTO.getType());
        testCase.setDescription(testCaseDTO.getDescription());
        testCase.setInputData(testCaseDTO.getInputData());
        testCase.setExpectedResult(testCaseDTO.getExpectedResult());
        testCase.setActualResult(testCaseDTO.getActualResult());
        testCase.setStatus(testCaseDTO.getStatus());
        testCase.setCreatedBy(testCaseDTO.getCreatedBy());
        return testCaseRepository.save(testCase);
    }

    public TestCase updateTestCase(Long id, TestCaseDTO testCaseDTO) {
        TestCase testCase = testCaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test Case not found"));
        Project project = projectRepository.findById(testCase.getProject().getId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        testCase.setProject(project);
        testCase.setTestCaseId(testCaseDTO.getTestCaseId());
        testCase.setTestCaseName(testCaseDTO.getTestCaseName());
        testCase.setType(testCaseDTO.getType());
        testCase.setDescription(testCaseDTO.getDescription());
        testCase.setInputData(testCaseDTO.getInputData());
        testCase.setExpectedResult(testCaseDTO.getExpectedResult());
        testCase.setActualResult(testCaseDTO.getActualResult());
        testCase.setStatus(testCaseDTO.getStatus());
        testCase.setCreatedBy(testCaseDTO.getCreatedBy());
        return testCaseRepository.save(testCase);
    }

    public List<TestCase> getTestCasesByUser(String createdBy) {

        return testCaseRepository.findByCreatedBy(createdBy);
    }

    public List<TestCase> getTestCasesByProjectAndUserName(Long projectId, String username) {
        // Admin sees all test cases; testers see only their own
        return testCaseRepository.findByProjectIdAndCreatedBy(projectId, username);
    }

    public List<TestCase> getTestCasesByProject(Long projectId) {
        // Admin sees all test cases; testers see only their own
        return testCaseRepository.findByProjectId(projectId);
    }
    public void deleteTestCase(Long id) {
        testCaseRepository.deleteById(id);
    }
}
