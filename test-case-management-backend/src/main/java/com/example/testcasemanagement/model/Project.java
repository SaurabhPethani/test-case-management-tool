package com.example.testcasemanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="active")
    private boolean active;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String jiraTicketId;

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJiraTicketId() {
        return jiraTicketId;
    }

    public void setJiraTicketId(String jiraTicketId) {
        this.jiraTicketId = jiraTicketId;
    }
}
