package com.example.testcasemanagement.repository;

import com.example.testcasemanagement.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
    List<TestCase> findByCreatedBy(String createdBy);

    List<TestCase> findByProjectId(Long projectId);

    List<TestCase> findByProjectIdAndCreatedBy(Long projectId, String createdBy);
}
