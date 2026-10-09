package com.example.student_app.service;

import com.example.student_app.entity.Student;
import com.example.student_app.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service //tells Spring that this class provides application/business logic
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();  //It asks the Repository to fetch the students from PostgreSQL.
    }

    // This creates a Student object and saves it to PostgreSQL through StudentRepository.
    public void saveStudent(String name, String email) {
        Student student = new Student(name, email);
        studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id) {
    return studentRepository.findById(id);
}

}