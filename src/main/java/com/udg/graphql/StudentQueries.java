package com.udg.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.udg.entities.Campus;
import com.udg.entities.Student;
import com.udg.repositories.CampusRepository;
import com.udg.repositories.StudentRepository;

@Controller
public class StudentQueries{
	@Autowired
	private CampusRepository campusRepository;
	
	@QueryMapping
	public List<Campus> getCampuses() {
		return campusRepository.findAll();
	}
}