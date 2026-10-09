package com.example.student_app.controller;

import com.example.student_app.entity.Student;
import com.example.student_app.service.StudentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class StudentController {

    private final StudentService studentService; //Spring automatically supplies the StudentService object when it creates the Controller.

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String students(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }
}