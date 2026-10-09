package com.example.student_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //Tells Spring that this class handles web requests and can return view names
public class StudentController {

    @GetMapping("/students") //Tells Spring to execute this method when the browser sends a GET request to /students.
    public String students(Model model) { //The Model carries data from the controller to the HTML template.
        model.addAttribute("message", "Welcome to Student Management System");
        return "students"; //tells Spring MVC to render the students.html view using Thymeleaf
    }
}
