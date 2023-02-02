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
import com.udg.entities.responses.Response;
import com.udg.services.CampusService;

@RestController
@Controller
@RequestMapping("/campus")
public class CampusController {
	@Autowired
	private CampusService campusService;
	private Response<Campus> response;
	
	@QueryMapping
	public Response<List<Campus>> getCampuses() {
		return campusService.getCampuses();
	}
	
	@QueryMapping
	public Response<Campus> getCampus(@Argument Long campusId) {
		return campusService.getCampus(campusId);
	}
	
	@MutationMapping
	public Response<Campus> createCampus(@Argument String name, @Argument String abbreviation, @Argument String address) {
		return campusService.createCampus(
				Campus.builder()
				.name(name)
				.abbreviation(abbreviation)
				.address(address)
				.build());
	}
	
	@MutationMapping
	public Response<Campus> updateCampus(@Argument String name, @Argument String abbreviation, @Argument String address) {
		return campusService.updateCampus(
				Campus.builder()
				.name(name)
				.abbreviation(abbreviation)
				.address(address)
				.build());
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