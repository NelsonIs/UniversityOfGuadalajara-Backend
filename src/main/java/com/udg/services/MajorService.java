package com.udg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udg.entities.Major;
import com.udg.entities.responses.Response;
import com.udg.repositories.MajorRepository;

@Service
public class MajorService {
	@Autowired
	private MajorRepository majorRepository;
	private Response<Major> response;
	
	public Response<Major> createMajor(Major major){
		response = new Response<>();
		try {
			response.setEntity(majorRepository.save(major));
			response.setMsg("Succes! Major created.");
		}catch(Exception e) {
			response = new Response<>();
			response.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return response;
	}
	
	public Response<Major> updateMajor(Major major){
		response = new Response<>();
		try {
			majorRepository.findById(major.getMajorId()).get();
			response.setEntity(majorRepository.save(major));
			response.setMsg("Succes! Major updated.");
		}catch(Exception e) {
			response = new Response<>();
			response.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return response;
	}
	
	public Response<Major> deleteMajor(Long majorId){
		response = new Response<>();
		try {
			response.setEntity(majorRepository.findById(majorId).get());
			majorRepository.deleteById(majorId);
			response.setMsg("Succes! Major deleted.");
		}catch(Exception e) {
			response = new Response<>();
			response.setMsg("Ups... Something was wrong: " + e.getMessage());
		}
		return response;
	}
}