package com.udg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udg.entities.Users;
import com.udg.entities.responses.Response;
import com.udg.repositories.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;
	private Response<Users> response;
	
	public Response<Users> createUser(Users user){
		response = new Response<>();
		try {
			response.setEntity(usersRepository.save(user));
			response.setMsg("Succes! User created.");
		}catch(Exception e) {
			response = new Response<>();
			response.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return response;
	}
	
	public Response<Users> updateUser(Users user){
		response = new Response<>();
		try {
			usersRepository.findById(user.getUsername()).get();
			response.setEntity(usersRepository.save(user));
			response.setMsg("Succes! User updated.");
		}catch(Exception e) {
			response = new Response<>();
			response.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return response;
	}
	
	public Response<Users> deleteUser(String username){
		response = new Response<>();
		try {
			response.setEntity(usersRepository.findById(username).get());
			usersRepository.deleteById(username);
			response.setMsg("Succes! User deleted.");
		}catch(Exception e) {
			response = new Response<>();
			response.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return response;
	}
}