package com.amigosCode.demo.db;

import org.springframework.stereotype.Repository;

import com.amigosCode.demo.models.Student;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

  boolean existsByEmail(String email);

}
