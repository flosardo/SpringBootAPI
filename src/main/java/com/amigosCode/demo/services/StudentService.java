package com.amigosCode.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amigosCode.demo.db.StudentRepository;
import com.amigosCode.demo.models.Student;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  public void addNewStudent(Student newStudent) {
    boolean studentExists = studentRepository.existsByEmail(newStudent.getEmail());
    if (!studentExists) {
      studentRepository.save(newStudent);
    } else
      throw new IllegalStateException("User with email " + newStudent.getEmail() + " already exists");
  }

  public void deleteStudent(Integer studentId) {
    boolean studentExists = studentRepository.existsById(studentId);
    if (studentExists) {
      studentRepository.deleteById(studentId);
    } else
      throw new IllegalStateException("User with ID " + studentId + " does not exist");
  }

  @Transactional
  public String updateStudent(Integer studentId, String name, String email) {
    String response = "There was an error updating the student";
    // Check if studentId is valid
    if (!studentRepository.existsById(studentId)) {
      throw new IllegalStateException("Student with ID " + studentId + " does not exist");
    }

    // Retrieve student from database
    Student student = studentRepository.findById(studentId).orElse(null);
    if (student == null) {
      throw new IllegalStateException("Student not found");
    }
    // Update student name and email if provided
    if (name != null && !student.getName().equals(name)) {
      student.setName(name);
      response = "Student updated successfully";
    }
    if (email != null && !student.getEmail().equals(email)) {
      // Check if email is taken by another user
      if (studentRepository.existsByEmail(email)) {
        throw new IllegalStateException("Email already taken by another user");
      }
      student.setEmail(email);
      response = "Student updated successfully";
    }
    return response;
  }
}
