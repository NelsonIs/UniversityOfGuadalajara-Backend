package com.udg.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.udg.entities.Student;
import com.udg.repositories.StudentRepository;

@Controller
public class StudentController2 {
	@Autowired
	private StudentRepository studentRepository;
	
	@QueryMapping
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
	
	@QueryMapping
	public Student getStudent(@Argument Long studentId) {
		return studentRepository.findById(studentId).get();
	}
	
	@MutationMapping
	public Student createStudent(@Argument Student student) {
		return studentRepository.save(student);
	}

}