package com.udg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udg.entities.Users;
import com.udg.entities.responses.LoginResponse;
import com.udg.repositories.UsersRepository;
//import com.udg.security.JWTGenerator;
import com.udg.security.JWTGenerator;

@Service
public class LoginService {
	@Autowired
	private UsersRepository usersRepository;
	
	public LoginResponse signin(Users user) {
		LoginResponse loginResponse = new LoginResponse();
		try {
			if(usersRepository.findById(user.getUsername()).get().getPassword().equals(user.getPassword())) {
				loginResponse.setMsg("Login Succes!");
				loginResponse.setToken(JWTGenerator.getJWTToken(user.getUsername()));
			}else {
				loginResponse.setMsg("Login Unsucces...");
			}
		}catch(Exception e) {
			loginResponse.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return loginResponse;
	}

}
