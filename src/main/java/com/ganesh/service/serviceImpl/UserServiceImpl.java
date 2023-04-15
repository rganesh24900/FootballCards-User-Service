package com.ganesh.service.serviceImpl;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganesh.entities.FootballCard;
import com.ganesh.entities.User;
import com.ganesh.exceptions.ResourceNotFoundException;
import com.ganesh.payloads.FootBallCardDto;
import com.ganesh.payloads.UserDto;
import com.ganesh.repository.FootballCardRepo;
import com.ganesh.repository.UserRepo;
import com.ganesh.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	FootballCardRepo footballCardRepo;
	
	public UserDto createUser(UserDto userDto) {
		User user = dtoToUser(userDto);
//		user.setFootballCards(userDto.getFootballCards().stream()
//				.map(this::dtoToCard).collect(Collectors.toList()));
		userRepo.save(user);
		return userToDto(user);
	}
	
	public UserDto updateUser(UserDto userDto,Long id) {
		User existingUser = userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id.intValue()));
		UserDto existingUserDto=userToDto(existingUser);
		existingUserDto.setUsername(userDto.getUsername());
		existingUserDto.setPassword(userDto.getPassword());
		return existingUserDto;
	}
	
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
	
	public List<UserDto> getAllUsers(){
		List<User> userList = userRepo.findAll();
		return userList.stream().map(user->userToDto(user)).collect(Collectors.toList());
	}
	
	public UserDto getUserDetails(Long id) {
		User existingUser = userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id.intValue()));
		return userToDto(existingUser);
	}
	
	
	public List<FootBallCardDto> getCardsByUserId(Long userId) {
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId.intValue()));
		if(!user.getFootballCards().isEmpty()) {
			return user.getFootballCards().stream().map(card->cardToDto(card)).collect(Collectors.toList());
		}
		return new ArrayList<FootBallCardDto>(0);
	}

	// To add existing card to existing user
	public Map<String,Object> setCardsToUser(Map<String,List<Long>> cardIdsMap , Long userId){
		Map<String,Object> result = new HashMap<>();
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId.intValue()));
		List<FootballCard> existingCards = footballCardRepo.findAll();
		List<FootballCard> cards = new ArrayList<>();
		for(FootballCard card : existingCards){
			if(cardIdsMap.get("cardIds").contains(card.getId())){
				cards.add(card);
			}
		}
		if (cards != null && !cards.isEmpty()){
			List<FootballCard> listOfCards = user.getFootballCards();
			listOfCards.addAll(cards);
			user.setFootballCards(listOfCards);
			userRepo.save(user);
			result.put("success",true);
			result.put("message","Cards successfully added to user");
			result.put("data",userToDto(user));
		}
		else {
			result.put("success",false);
		    result.put("message","Bad Request");
		}
		return result;
	}

	private User dtoToUser(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}
	
	private UserDto userToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}
	
	private FootballCard dtoToCard(FootBallCardDto cardDto) {
		return modelMapper.map(cardDto, FootballCard.class);
	}
	
	private FootBallCardDto cardToDto(FootballCard card) {
		return modelMapper.map(card, FootBallCardDto.class);
	}
}
