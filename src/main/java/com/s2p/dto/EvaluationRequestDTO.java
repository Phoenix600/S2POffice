package com.s2p.dto;

import java.util.List;

public class EvaluationRequestDTO {
    private String studentEmail;
    private String title;   // using title instead of paperCode
    private List<String> studentAnswers;

    // Getters & Setters
    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(List<String> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }
}
