package com.example.testcasemanagement.controller;

import com.example.testcasemanagement.dto.TestCaseDTO;
import com.example.testcasemanagement.model.TestCase;
import com.example.testcasemanagement.model.User;
import com.example.testcasemanagement.service.TestCaseService;
import com.example.testcasemanagement.service.UserService;
import com.example.testcasemanagement.util.ApiResponse;
import com.example.testcasemanagement.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testcases")
public class TestCaseController {

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<TestCase>> createTestCase(
            @RequestBody TestCaseDTO testCase,
            @AuthenticationPrincipal UserDetails userDetails) {
        try{
            testCase.setCreatedBy(userDetails.getUsername());
            TestCase createdTestCase = testCaseService.createTestCase(testCase);
            return ResponseEntity.ok(new ApiResponse<>(createdTestCase, " created successfully"));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, e.getMessage()));
        }
    }

    /**
     * instead of fetching user we can use jwt token to get role of user & return response
     * @return
     */
    @GetMapping("/{projectId}")
    public ResponseEntity<ApiResponse<List<TestCase>>> getTestCasesByProject(
            @PathVariable Long projectId,
            @AuthenticationPrincipal UserDetails userDetails) {
        try{
            if(Utilities.isAdmin(userDetails)) {
                List<TestCase> testCasesByProject = testCaseService.getTestCasesByProject(projectId);
                return ResponseEntity.ok(new ApiResponse<>(testCasesByProject, "found "));
            }

            List<TestCase> testCases = testCaseService.getTestCasesByProjectAndUserName(projectId, userDetails.getUsername());
            return ResponseEntity.ok(new ApiResponse<>(testCases, "found"));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, e.getMessage()));
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TestCase>> updateTestCase(@PathVariable Long id, @RequestBody TestCaseDTO testCaseDTO) {
        try {
            TestCase testCase = testCaseService.updateTestCase(id, testCaseDTO);
            return ResponseEntity.ok(new ApiResponse<>(testCase, "updated "));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Long>> deleteTestCase(@PathVariable Long id) {
        try {
            testCaseService.deleteTestCase(id);
            return ResponseEntity.ok(new ApiResponse<>(id, "deleted "));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(null, e.getMessage()));
        }
    }
}
