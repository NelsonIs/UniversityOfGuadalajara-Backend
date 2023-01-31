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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udg.entities.Campus;
import com.udg.entities.responses.Response;
import com.udg.repositories.CampusRepository;
import com.udg.services.CampusService;

@RestController
@Controller
@RequestMapping("/campus")
public class CampusController {
	@Autowired
	private CampusService campusService;
	@Autowired
	private CampusRepository campusRepository;
	private Response<Campus> response;
	
	@QueryMapping
	public List<Campus> getCampuses() {
		return campusRepository.findAll();
	}
	
	@QueryMapping
	public Campus getCampus(@Argument Long campusId) {
		return campusRepository.findById(campusId).get();
	}
	
	@MutationMapping
	public Campus createCampus(@Argument String name, @Argument String abbreviation, @Argument String address) {
		return campusRepository.save(
				Campus.builder()
				.name(name)
				.abbreviation(abbreviation)
				.address(address)
				.build());
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