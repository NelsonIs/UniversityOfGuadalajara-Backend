package com.udg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udg.entities.Student;
import com.udg.entities.responses.Response;
import com.udg.repositories.StudentRepository;
import com.udg.services.StudentService;

@RestController
@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private StudentRepository studentRepository;
	private Response<Student> response;
	
	@QueryMapping
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
	
	@QueryMapping
	public Student getStudent(@Argument Long studentId) {
		return studentRepository.findById(studentId).get();
	}
	
	@PostMapping
	public ResponseEntity<Response<Student>> createStudent(@RequestBody Student student){
		response = studentService.createStudent(student);
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<Student>>(response, HttpStatus.CREATED);
		}
		return new ResponseEntity<Response<Student>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping
	public ResponseEntity<Response<Student>> updateStudent(@RequestBody Student student){
		response = studentService.updateStudent(student);
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<Student>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response<Student>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{studentId}")
	public ResponseEntity<Response<Student>> deleteStudent(@PathVariable("studentId") Long studentId){
		response = studentService.deleteStudent(studentId);
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<Student>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response<Student>>(response, HttpStatus.BAD_REQUEST);
	}
}