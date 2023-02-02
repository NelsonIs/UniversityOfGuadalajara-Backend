package com.udg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udg.entities.Campus;
import com.udg.entities.Major;
import com.udg.entities.Student;
import com.udg.entities.responses.Response;
import com.udg.services.StudentService;

@RestController
@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentService;
	private Response<Student> response;
	
	@QueryMapping
	public Response<List<Student>> getStudents() {
		return studentService.getStudents();
	}
	
	@QueryMapping
	public Response<Student> getStudent(@Argument Long studentId) {
		return studentService.getStudent(studentId);
	}
	
	@MutationMapping
	public Response<Student> createStudent(@Argument Long studentId, @Argument String firstName, @Argument String lastName,
			@Argument String email, @Argument Major major, @Argument Campus campus) {
		return studentService.createStudent(Student.builder()
				.studentId(studentId)
				.firstName(firstName)
				.lastName(lastName)
				.email(email)
				.major(major)
				.campus(campus)
				.build());
	}
	
	@MutationMapping
	public Response<Student> updateStudent(@Argument Long studentId, @Argument String firstName, @Argument String lastName,
			@Argument String email, @Argument Major major, @Argument Campus campus) {
		return studentService.updateStudent(Student.builder()
				.studentId(studentId)
				.firstName(firstName)
				.lastName(lastName)
				.email(email)
				.major(major)
				.campus(campus)
				.build());
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