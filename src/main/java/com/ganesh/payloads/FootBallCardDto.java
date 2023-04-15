package com.ganesh.payloads;

import java.util.List;

import com.ganesh.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FootBallCardDto {

	String playerName;

	String image;

	Long dribble;

	Long speed;

	Long weakFootAcc;

	String playingStyle;
	
	String playerPos;

	Long jerseyNumber;

}
