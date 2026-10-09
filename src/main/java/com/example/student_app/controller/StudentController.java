package com.example.student_app.controller;

import com.example.student_app.entity.Student;
import com.example.student_app.service.StudentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Controller
public class StudentController {

    private final StudentService studentService; // Spring automatically supplies the StudentService object when it creates the Controller.

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String students(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    @PostMapping("/students")
    public String addStudent(
            @RequestParam String name,
            @RequestParam String email,
            RedirectAttributes redirectAttributes) {
        // RedirectAttributes is an object provided by Spring MVC. It lets you pass data
        // to the page you redirect to.
        
        studentService.saveStudent(name, email); // buisness logic

        redirectAttributes.addFlashAttribute( //It stores a temporary message under the key "message".
                "message", "Student added successfully!");

        return "redirect:/students";
    }
    // The redirect reloads the student list after saving. This helps prevent
    // accidental duplicate submissions when the page is refreshed.

    @GetMapping("/students/{id}")
    public String studentDetails(
            @PathVariable Long id,
            Model model) {

        Student student = studentService.getStudentById(id)
            .orElseThrow(() ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Student not found"));

        model.addAttribute("student", student); //passes the student to Thymeleaf.

        return "student_details";
    }

    @GetMapping("/students/edit/{id}")
        public String editStudent(@PathVariable Long id, Model model) {

            Student student = studentService.getStudentById(id)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            model.addAttribute("student", student);

            return "edit_student";
        }

    @PostMapping("/students/update")
    public String updateStudent(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String email,
            RedirectAttributes redirectAttributes) {

        studentService.updateStudent(id, name, email);

        redirectAttributes.addFlashAttribute(
                "message", "Student updated successfully!");

        return "redirect:/students";
    }


    @PostMapping("/students/delete/{id}")
    public String deleteStudent(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        studentService.deleteStudent(id);

        redirectAttributes.addFlashAttribute(
                "message", "Student deleted successfully!");

        return "redirect:/students";
    }

}