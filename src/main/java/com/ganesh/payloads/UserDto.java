package com.ganesh.payloads;

import java.util.List;

import com.ganesh.entities.FootballCard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
    String username;
	
	String password;
	
	List<FootBallCardDto> footballCards;

}
