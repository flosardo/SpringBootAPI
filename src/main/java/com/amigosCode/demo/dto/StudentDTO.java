package com.amigosCode.demo.dto;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class StudentDTO {
  private String name;
  private String email;
  private LocalDate birth;
  @Transient
  private Integer age;

  public Integer getAge() {
    return Period.between(this.birth, LocalDate.now()).getYears();
  }

  public StudentDTO(String name, String email, LocalDate birth) {
    this.name = name;
    this.email = email;
    this.birth = birth;
  }

}
