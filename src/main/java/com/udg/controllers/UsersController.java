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

import com.udg.entities.Users;
import com.udg.entities.responses.Response;
import com.udg.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private UsersService usersService;
	private Response<Users> response;
	
	@GetMapping
	public ResponseEntity<Response<List<Users>>> getUsers(){
		Response<List<Users>> response = new Response<>();
		response = usersService.getUsers();
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<List<Users>>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response<List<Users>>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<Response<Users>> getUser(@PathVariable("username") String username){
		response = usersService.getUser(username);
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<Users>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response<Users>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping
	public ResponseEntity<Response<Users>> createUser(@RequestBody Users user){
		response = usersService.createUser(user);
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<Users>>(response, HttpStatus.CREATED);
		}
		return new ResponseEntity<Response<Users>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping
	public ResponseEntity<Response<Users>> updateUser(@RequestBody Users user){
		response = usersService.updateUser(user);
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<Users>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response<Users>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<Response<Users>> deleteUser(@PathVariable("username") String username){
		response = usersService.deleteUser(username);
		if(response.getEntity() != null) {
			return new ResponseEntity<Response<Users>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Response<Users>>(response, HttpStatus.BAD_REQUEST);
	}
}