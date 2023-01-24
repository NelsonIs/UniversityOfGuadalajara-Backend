package com.udg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udg.entities.Users;

public interface UsersRepository extends JpaRepository<Users, String> {

}