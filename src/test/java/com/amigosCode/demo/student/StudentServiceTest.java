package com.amigosCode.demo.student;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.amigosCode.demo.dto.StudentDTO;
import com.amigosCode.demo.mapper.StudentDTOToStudent;
import com.amigosCode.demo.persistence.entity.Student;
import com.amigosCode.demo.persistence.repository.StudentRepository;
import com.amigosCode.demo.service.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

  @Mock
  private StudentRepository studentRepository;
  private StudentService underTest;

  @BeforeEach
  void setUp() {
    // autoCloseable = MockitoAnnotations.openMocks(this);
    underTest = new StudentService(studentRepository, new StudentDTOToStudent());
  }

  @Test
  void canGetStudents() {
    // when
    underTest.getStudents();
    // then
    verify(studentRepository).findAll();
  }

  @Test
  void shouldReturnStudentWhenExists() {
    // given
    Integer studentId = 1;
    Student expectedStudent = new Student("John", "john@example.com", LocalDate.of(2000, 1, 1));
    when(studentRepository.findById(studentId)).thenReturn(Optional.of(expectedStudent));

    // when
    Student actualStudent = underTest.getAStudent(studentId);

    // then
    assertEquals(expectedStudent, actualStudent);
  }

  @Test
  void shouldThrowWhenStudentDoesNotExist() {
    // given
    Integer studentId = 1;
    when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

    // when
    Exception exception = assertThrows(IllegalStateException.class, () -> {
      underTest.getAStudent(studentId);
    });

    // then
    String expectedMessage = "Student with ID " + studentId + " not found";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void canAddNewStudent() {
    // Given
    StudentDTO studentDTO = new StudentDTO("Alice", "alice@example.com", LocalDate.of(2000, 1, 1));
    Student student = new Student("Alice", "alice@example.com", LocalDate.of(2000, 1, 1));
    when(studentRepository.existsByEmail("alice@example.com")).thenReturn(false);
    // When
    underTest.addNewStudent(studentDTO);

    // Then
    ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
    verify(studentRepository).save(studentArgumentCaptor.capture());
    Student capturedStudent = studentArgumentCaptor.getValue();
    assertThat(capturedStudent.getName()).isEqualTo(student.getName());
    assertThat(capturedStudent.getEmail()).isEqualTo(student.getEmail());

  }

  @Test
  void addNewStudentWhenEmailTaken() {
    // given
    StudentDTO student = new StudentDTO("Carlos", "carlos@gmail.com", LocalDate.of(1990, 10, 19));

    // when
    given(studentRepository.existsByEmail(student.getEmail())).willReturn(true);
    // then
    assertThatExceptionOfType(IllegalStateException.class)
        .isThrownBy(() -> underTest.addNewStudent(student))
        .withMessageContaining("User with email " + student.getEmail() + " already exists");
    verify(studentRepository, never()).save(any());
  }

  @Test
  void deleteStudent() {
    // given
    Integer studentId = 1;
    given(studentRepository.existsById(studentId)).willReturn(true);
    // when
    underTest.deleteStudent(studentId);
    // then
    verify(studentRepository).deleteById(studentId);
  }

  @Test
  void deleteStudentWhenIdDoesNotExist() {
    Integer studentId = 1;

    // when
    Exception exception = assertThrows(IllegalStateException.class, () -> {
      underTest.deleteStudent(studentId);
    });

    // then
    String expectedMessage = "User with ID " + studentId + " does not exist";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

}