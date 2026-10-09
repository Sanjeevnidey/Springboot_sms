package com.example.student_app.controller;

import com.example.student_app.service.StudentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    private final StudentService studentService; //Spring automatically supplies the StudentService object when it creates the Controller.

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String students(Model model) {

        String message = studentService.getWelcomeMessage();  //asks the Service to provide the message.
        model.addAttribute("message", message);

        return "students";
    }
}