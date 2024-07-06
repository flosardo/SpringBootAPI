package com.amigosCode.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amigosCode.demo.dto.StudentDTO;
import com.amigosCode.demo.persistence.entity.Student;
import com.amigosCode.demo.service.StudentService;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping
  public ResponseEntity<List<Student>> getAllStudents() {
    return ResponseEntity.ok(studentService.getStudents());
  }

  @GetMapping("/{studentId}")
  public ResponseEntity<Student> getSpecificStudent(@PathVariable Integer studentId) {
    return ResponseEntity.ok(studentService.getAStudent(studentId));
  }

  @PostMapping
  public ResponseEntity<Student> registerNewStudent(@RequestBody StudentDTO student) {
    Student createdStudent = studentService.addNewStudent(student);
    return ResponseEntity.ok(createdStudent);
  }

  @DeleteMapping("/{studentId}")
  public ResponseEntity<?> deleteStudent(@PathVariable Integer studentId) {
    studentService.deleteStudent(studentId);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{studentId}") // ver si lo adapto para que recibe todo un objeto StudentDTO
  public ResponseEntity<?> updateStudent(@PathVariable Integer studentId, @RequestParam(required = false) String name,
      @RequestParam(required = false) String email) {
    studentService.updateStudent(studentId, name, email);
    return ResponseEntity.ok().build();
  }
}
