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

import com.udg.entities.TypeUsers;
import com.udg.entities.Users;
import com.udg.entities.responses.Response;
import com.udg.services.UsersService;

@RestController
@Controller
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private UsersService usersService;
	private Response<Users> response;
	
	@QueryMapping
	public Response<List<Users>> getUsers() {
		return usersService.getUsers();
	}
	
	@QueryMapping
	public Response<Users> getUser(@Argument String username) {
		return usersService.getUser(username);
	}
	
	@MutationMapping
	public Response<Users> createUser(@Argument String username, @Argument String password, 
			@Argument String email, @Argument TypeUsers role, @Argument String firstName, @Argument String lastName){
		return usersService.createUser(Users.builder()
				.username(username)
				.password(password)
				.email(email)
				.role(role)
				.firstName(firstName)
				.lastName(lastName)
				.build());
	}
	
	@MutationMapping
	public Response<Users> updateUser(@Argument String username, @Argument String password, 
			@Argument String email, @Argument TypeUsers role, @Argument String firstName, @Argument String lastName){
		return usersService.updateUser(Users.builder()
				.username(username)
				.password(password)
				.email(email)
				.role(role)
				.firstName(firstName)
				.lastName(lastName)
				.build());
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