package com.udg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udg.entities.Student;
import com.udg.entities.responses.Response;
import com.udg.repositories.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	private Response<Student> response;
	
	public Response<List<Student>> getStudents(){
		Response<List<Student>> response = new Response<>();
		try {
			response.setEntity(studentRepository.findAll());
			response.setMsg(studentRepository.count() == 0 ? "Ups.. your DataBase is empty.": "Succes! Students found.");
		}catch(Exception e) {
			response = new Response<>();
			response.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return response;
	}
	
	public Response<Student> getStudent(Long studentId){
		response = new Response<>();
		try {
			response.setEntity(studentRepository.findById(studentId).get());
			response.setMsg("Succes! Student found.");
		}catch(Exception e) {
			response = new Response<>();
			response.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return response;
	}
	
	public Response<Student> createStudent(Student student){
		response = new Response<>();
		try {
			response.setEntity(studentRepository.save(student));
			response.setMsg("Succes! Student created.");
		}catch(Exception e) {
			response = new Response<>();
			response.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return response;
	}
	
	public Response<Student> updateStudent(Student student){
		response = new Response<>();
		try {
			studentRepository.findById(student.getStudentId()).get();
			response.setEntity(studentRepository.save(student));
			response.setMsg("Succes! Student updated.");
		}catch(Exception e) {
			response = new Response<>();
			response.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return response;
	}
	
	public Response<Student> deleteStudent(Long studentId){
		response = new Response<>();
		try {
			response.setEntity(studentRepository.findById(studentId).get());
			studentRepository.deleteById(studentId);
			response.setMsg("Succes! Student deleted.");
		}catch(Exception e) {
			response = new Response<>();
			response.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return response;
	}
}