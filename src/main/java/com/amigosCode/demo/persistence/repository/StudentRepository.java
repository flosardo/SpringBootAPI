package com.amigosCode.demo.persistence.repository;

import org.springframework.stereotype.Repository;

import com.amigosCode.demo.persistence.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

  boolean existsByEmail(String email);

}
