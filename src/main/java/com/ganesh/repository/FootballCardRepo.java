package com.ganesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ganesh.entities.FootballCard;

public interface FootballCardRepo extends JpaRepository<FootballCard, Long>{
	

}
