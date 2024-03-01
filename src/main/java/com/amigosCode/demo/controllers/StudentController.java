package com.amigosCode.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<List<Student>> getAllStudents() {
    return ResponseEntity.ok(studentService.getStudents());
  }

  @GetMapping("api/v1/students/{studentId}")
  public ResponseEntity<Student> getSpecificStudent(@PathVariable Integer studentId) {
    return ResponseEntity.ok(studentService.getAStudent(studentId));
  }

  @PostMapping("api/v1/students")
  public ResponseEntity<Student> registerNewStudent(@RequestBody Student student) {
    studentService.addNewStudent(student);
    return ResponseEntity.ok(student);
  }

  @DeleteMapping("api/v1/students/{studentId}")
  public ResponseEntity<?> deleteStudent(@PathVariable Integer studentId) {
    studentService.deleteStudent(studentId);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("api/v1/students/{studentId}")
  public ResponseEntity<?> updateStudent(@PathVariable Integer studentId, @RequestParam(required = false) String name,
      @RequestParam(required = false) String email) {
    studentService.updateStudent(studentId, name, email);
    return ResponseEntity.ok().build();
  }
}
