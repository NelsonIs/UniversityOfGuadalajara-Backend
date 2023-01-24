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

import com.udg.entities.Campus;
import com.udg.entities.responses.Response;
import com.udg.services.CampusService;

@RestController
@RequestMapping("/campus")
public class CampusController {
	@Autowired
	private CampusService campusService;
	private Response<Campus> response;
	
	@GetMapping
	public ResponseEntity<Response<List<Campus>>> getCampuses(){
		Response<List<Campus>> response = new Response<>();
		response = campusService.getCampuses();
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<List<Campus>>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response<List<Campus>>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{campusId}")
	public ResponseEntity<Response<Campus>> getCampus(@PathVariable("campusId") Long campusId){
		response = campusService.getCampus(campusId);
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<Campus>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response<Campus>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping
	public ResponseEntity<Response<Campus>> createCampus(@RequestBody Campus campus){
		response = campusService.createCampus(campus);
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<Campus>>(response, HttpStatus.CREATED);
		}
		return new ResponseEntity<Response<Campus>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping
	public ResponseEntity<Response<Campus>> updateCampus(@RequestBody Campus campus){
		response = campusService.updateCampus(campus);
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<Campus>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response<Campus>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{campusId}")
	public ResponseEntity<Response<Campus>> deleteCampus(@PathVariable("campusId") Long campusId){
		response = campusService.deleteCampus(campusId);
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<Campus>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response<Campus>>(response, HttpStatus.BAD_REQUEST);
	}
}