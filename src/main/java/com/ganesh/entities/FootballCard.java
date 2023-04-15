package com.ganesh.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "football_card")
public class FootballCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String playerName;

	String image;
	
	Long dribble;
	
	Long speed;
	
	Long weakFootAcc;
	
	String playingStyle;

	String playerPos;
	
	Long jerseyNumber;
	
	@ManyToMany(mappedBy ="footballCards", fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	List<User> userList;

}
