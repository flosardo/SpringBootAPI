package com.amigosCode.demo.models;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "student")
public class Student {

  @Id
  @Getter
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Getter
  @Setter
  @Column
  private String name;

  @Getter
  @Setter
  private String email;

  @Getter
  @Setter
  private LocalDate birth;

  @Transient
  private Integer age;

  public Integer getAge() {
    return Period.between(this.birth, LocalDate.now()).getYears();
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + this.id +
        ", name='" + this.name + '\'' +
        ", email='" + this.email + '\'' +
        ", birth=" + this.birth +
        ", age=" + this.age +
        '}';
  }

  public Student(String name, String email, LocalDate birth) {
    this.name = name;
    this.email = email;
    this.birth = birth;
  }

  public Student() {
  }

}
