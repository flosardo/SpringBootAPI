package com.amigosCode.demo.student;

import com.amigosCode.demo.db.StudentRepository;
import com.amigosCode.demo.models.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class StudentRepositoryTest {

  @Autowired
  private StudentRepository underTest;

  @AfterEach
  void tearDown() {
    underTest.deleteAll();
  }

  @Test
  void itShouldCheckIfStudentEmailExists() {
    // given
    String email = "carlos90@hotmail.com";
    Student student = new Student("Carlos", email, LocalDate.of(1995, 10, 15));
    underTest.save(student);
    // when
    boolean result = underTest.existsByEmail(email);
    // then
    assertEquals(true, result);
  }

  @Test
  void itShouldCheckIfStudentEmailNotExists() {
    // given
    String email = "random0@hotmail.com";
    // when
    boolean result = underTest.existsByEmail(email);
    // then
    assertEquals(false, result);
  }

}
