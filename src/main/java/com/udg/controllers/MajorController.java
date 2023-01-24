package com.udg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udg.entities.Major;
import com.udg.entities.responses.Response;
import com.udg.services.MajorService;

@RestController
@RequestMapping("/majors")
public class MajorController {
	@Autowired
	private MajorService majorService;
	private Response<Major> response;
	
	@GetMapping
	public ResponseEntity<Response<List<Major>>> getMajors(){
		Response<List<Major>> response = new Response<>();
		response = majorService.getMajors();
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<List<Major>>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response<List<Major>>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{majorId}")
	public ResponseEntity<Response<Major>> getMajor(@PathVariable("majorId") Long majorId){
		response = majorService.getMajor(majorId);
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<Major>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response<Major>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping
	public ResponseEntity<Response<Major>> createMajor(@RequestBody Major major){
		response = majorService.createMajor(major);
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<Major>>(response, HttpStatus.CREATED);
		}
		return new ResponseEntity<Response<Major>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping
	public ResponseEntity<Response<Major>> updateMajor(@RequestBody Major major){
		response = majorService.updateMajor(major);
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<Major>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response<Major>>(response, HttpStatus.BAD_REQUEST);
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