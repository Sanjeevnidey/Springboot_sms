package com.example.student_app.service;

import org.springframework.stereotype.Service;

@Service //tells Spring that this class provides application/business logic
public class StudentService {

    public String getWelcomeMessage() {
        return "Welcome to Student Management System";
    }
}