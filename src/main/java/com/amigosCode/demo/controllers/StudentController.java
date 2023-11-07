package com.amigosCode.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amigosCode.demo.models.Student;
import com.amigosCode.demo.services.StudentService;

@RestController
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping("api/v1/students")
  public List<Student> getAllStudents() {
    return studentService.getStudents();
  }

  @PostMapping("api/v1/students")
  public void registerNewStudent(@RequestBody Student student) {
    studentService.addNewStudent(student);
  }

  @DeleteMapping("api/v1/students/{studentId}")
  public void deleteStudent(@PathVariable Integer studentId) {
    studentService.deleteStudent(studentId);
  }

  @PutMapping("api/v1/students/{studentId}")
  public void updateStudent(@PathVariable Integer studentId, @RequestParam(required = false) String name,
      @RequestParam(required = false) String email) {
    studentService.updateStudent(studentId, name, email);
  }
}
