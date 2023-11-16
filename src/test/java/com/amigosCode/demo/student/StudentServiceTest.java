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

import com.amigosCode.demo.db.StudentRepository;
import com.amigosCode.demo.models.Student;
import com.amigosCode.demo.services.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

  @Mock
  private StudentRepository studentRepository;
  // private AutoCloseable autoCloseable;
  private StudentService underTest;

  @BeforeEach
  void setUp() {
    // autoCloseable = MockitoAnnotations.openMocks(this);
    underTest = new StudentService(studentRepository);
  }

  // @AfterEach
  // void tearDown() throws Exception {
  // autoCloseable.close();
  // }

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
    // given
    Student student = new Student("Carlos", "carlos@gmail.com", LocalDate.of(1990, 10, 19));
    // when
    underTest.addNewStudent(student);
    // then
    ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

    verify(studentRepository).save(studentArgumentCaptor.capture());

    Student capturedStudent = studentArgumentCaptor.getValue();

    System.out.println(capturedStudent.getName());
    assertThat(capturedStudent).isEqualTo(student);
    // System.out.println(studentRepository.count());
    // verify(studentRepository).save(student);
  }

  @Test
  void addNewStudentWhenEmailTaken() {
    // given
    Student student = new Student("Carlos", "carlos@gmail.com", LocalDate.of(1990, 10, 19));
    // Student invalidStudent = new Student("Martin", "carlos@gmail.com",
    // LocalDate.of(1990, 10, 19));

    // when
    given(studentRepository.existsByEmail(student.getEmail())).willReturn(true);
    // underTest.addNewStudent(invalidStudent);
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