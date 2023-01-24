package com.udg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.udg.entities.Users;
import com.udg.entities.responses.LoginResponse;
import com.udg.services.LoginService;

@RestController
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@PostMapping("/signin")
	public ResponseEntity<LoginResponse> signin(@RequestBody Users User){
		LoginResponse loginResponse = loginService.signin(User);
		if(loginResponse.getToken() != null) {
			return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
		}else {
			return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
		}
	}
}