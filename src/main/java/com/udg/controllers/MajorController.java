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

import com.udg.entities.AreasOfMajors;
import com.udg.entities.Major;
import com.udg.entities.responses.Response;
import com.udg.services.MajorService;

@RestController
@Controller
@RequestMapping("/majors")
public class MajorController {
	@Autowired
	private MajorService majorService;
	private Response<Major> response;
	
	@QueryMapping
	public Response<List<Major>> getMajors() {
		return majorService.getMajors();
	}
	
	@QueryMapping
	public Response<Major> getMajor(@Argument Long majorId) {
		return majorService.getMajor(majorId);
	}
	
	@MutationMapping
	public Response<Major> createMajor(@Argument String name, @Argument int numOfSemesters, @Argument AreasOfMajors area) {
		return majorService.createMajor(Major.builder()
				.name(name)
				.numOfSemesters(numOfSemesters)
				.area(area)
				.build());
	}
	
	@MutationMapping
	public Response<Major> updateMajor(@Argument String name, @Argument int numOfSemesters, @Argument AreasOfMajors area) {
		return majorService.updateMajor(Major.builder()
				.name(name)
				.numOfSemesters(numOfSemesters)
				.area(area)
				.build());
	}
	
	@DeleteMapping("/{majorId}")
	public ResponseEntity<Response<Major>> deleteMajor(@PathVariable("majorId") Long majorId){
		response = majorService.deleteMajor(majorId);
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<Major>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response<Major>>(response, HttpStatus.BAD_REQUEST);
	}
}