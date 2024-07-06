package com.amigosCode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amigosCode.demo.dto.StudentDTO;
import com.amigosCode.demo.mapper.StudentDTOToStudent;
import com.amigosCode.demo.persistence.entity.Student;
import com.amigosCode.demo.persistence.repository.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;
  @Autowired
  private StudentDTOToStudent mapper;

  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  public Student getAStudent(Integer studentId) {
    Student student = studentRepository.findById(studentId).orElse(null);
    if (student == null) {
      throw new IllegalStateException("Student with ID " + studentId + " not found");
    }
    return student;
  }

  public Student addNewStudent(StudentDTO newStudentDTO) {
    boolean studentExists = studentRepository.existsByEmail(newStudentDTO.getEmail());
    if (!studentExists) {
      Student newStudent = mapper.map(newStudentDTO);
      return studentRepository.save(newStudent);
    } else
      throw new IllegalStateException("User with email " + newStudentDTO.getEmail() + " already exists");
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
    if (!studentRepository.existsById(studentId)) {
      throw new IllegalStateException("Student with ID " + studentId + " does not exist");
    }
    Student student = studentRepository.findById(studentId).orElse(null);
    if (name != null && !student.getName().equals(name)) {
      student.setName(name);
      response = "Student updated successfully";
    }
    if (email != null && !student.getEmail().equals(email)) {
      if (studentRepository.existsByEmail(email)) {
        throw new IllegalStateException("Email already taken by another user");
      }
      student.setEmail(email);
      response = "Student updated successfully";
    }
    return response;
  }
}
