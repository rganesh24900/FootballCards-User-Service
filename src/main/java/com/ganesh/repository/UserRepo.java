package com.ganesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ganesh.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
