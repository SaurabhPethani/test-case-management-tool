package com.example.testcasemanagement.dto;

public class ProjectDTO {

    private String name;
    private String jiraTicketId;

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
