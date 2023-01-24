package com.udg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udg.entities.Campus;

public interface CampusRepository extends JpaRepository<Campus, Long> {

}