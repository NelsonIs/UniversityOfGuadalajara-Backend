package com.udg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udg.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}