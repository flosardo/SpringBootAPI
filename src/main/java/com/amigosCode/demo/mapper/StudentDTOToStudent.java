package com.amigosCode.demo.mapper;

import org.springframework.stereotype.Component;

import com.amigosCode.demo.dto.StudentDTO;
import com.amigosCode.demo.persistence.entity.Student;

@Component
public class StudentDTOToStudent implements IMapper<StudentDTO, Student> {

  @Override
  public Student map(StudentDTO input) {
    return new Student(input.getName(), input.getEmail(), input.getBirth());
  }

}
