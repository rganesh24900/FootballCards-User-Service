package com.ganesh.service;

import java.util.List;
import java.util.Map;

import com.ganesh.payloads.FootBallCardDto;
import com.ganesh.payloads.UserDto;


public interface UserService {
	
	 UserDto createUser(UserDto userDto);
	
	 List<UserDto> getAllUsers();
	
	 UserDto getUserDetails(Long id);
	 
	 UserDto updateUser(UserDto userDto , Long id);
	 
	 void deleteUser(Long id);

	 List<FootBallCardDto> getCardsByUserId(Long userId);

	Map<String,Object> setCardsToUser(Map<String,List<Long>> cardIdsMap , Long cardId);

}
