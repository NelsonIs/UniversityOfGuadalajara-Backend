package com.udg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udg.entities.Campus;
import com.udg.entities.responses.Response;
import com.udg.repositories.CampusRepository;

@Service
public class CampusService {
	@Autowired
	private CampusRepository campusRepository;
	private Response<Campus> response;
	
	public Response<List<Campus>> getCampuses(){
		Response<List<Campus>> response = new Response<>();
		try {
			response.setEntity(campusRepository.findAll());
			response.setMsg(campusRepository.count() == 0 ? "Ups.. Your DataBase is empty.": "Succes! Campuses found.");
		}catch(Exception e) {
			response = new Response<>();
			response.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return response;
	}
	
	public Response<Campus> getCampus(Long campusId){
		response = new Response<>();
		try {
			response.setEntity(campusRepository.findById(campusId).get());
			response.setMsg("Succes! Campus found.");
		}catch(Exception e) {
			response = new Response<>();
			response.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return response;
	}
	
	public Response<Campus> createCampus(Campus campus){
		response = new Response<>();
		try {
			response.setEntity(campusRepository.save(campus));
			response.setMsg("Succes! Campus created.");
		}catch(Exception e) {
			response = new Response<>();
			response.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return response;
	}
	
	public Response<Campus> updateCampus(Campus campus){
		response = new Response<>();
		try {
			campusRepository.findById(campus.getCampusId()).get();
			response.setEntity(campusRepository.save(campus));
			response.setMsg("Succes! Campus updated.");
		}catch(Exception e) {
			response = new Response<>();
			response.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return response;
	}
	
	public Response<Campus> deleteCampus(Long campusId){
		response = new Response<>();
		try {
			response.setEntity(campusRepository.findById(campusId).get());
			campusRepository.deleteById(campusId);
			response.setMsg("Succes! Campus deleted.");
		}catch(Exception e) {
			response = new Response<>();
			response.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return response;
	}
}