package com.ganesh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ganesh.payloads.ApiDataResponse;
import com.ganesh.payloads.FootBallCardDto;
import com.ganesh.payloads.UserDto;
import com.ganesh.service.UserService;


	@RestController
	@RequestMapping("api/users")
	public class UserController {


		@Autowired
		UserService userService;
		
	  @GetMapping("/getAllUsers")
	  public ResponseEntity<List<UserDto>> getAllUsers(){
		  List<UserDto> getAllUserDtos = userService.getAllUsers();
		 return new ResponseEntity<>(getAllUserDtos,HttpStatus.FOUND);
	 }
	  
	  @GetMapping("/getUser/{id}")
	  public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
		  UserDto getUserDto = userService.getUserDetails(id);
		  return new ResponseEntity<>(getUserDto,HttpStatus.OK);
	  }
	  
	  @PostMapping("/createUser")
	  public ResponseEntity<UserDto> createUser(@Validated  @RequestBody UserDto userDto){
		  return new ResponseEntity<>(userService.createUser(userDto),HttpStatus.CREATED);
	  }
	  
	  @PutMapping("/updateUser/{id}")
	  public ResponseEntity<UserDto> updateUser(@Validated @RequestBody UserDto userDto,@PathVariable("id") Long id){
		  return new ResponseEntity<UserDto>(userService.updateUser(userDto, id),HttpStatus.OK);
	  }
	  
	  @DeleteMapping("/removeUser/{id}")
	  public ResponseEntity<?> removeUser(@PathVariable("id") Long id){
		  userService.deleteUser(id);
		  return ResponseEntity.ok(new ApiDataResponse("User deleted sucessfully",false));
	  }
		
	  @GetMapping("/getCardsByUserId/{id}")
	  public ResponseEntity<List<FootBallCardDto>> getCardsByUserId(@PathVariable("id") Long id){ 
		  return new  ResponseEntity<>(userService.getCardsByUserId(id),HttpStatus.OK);
	  }

      @PostMapping("/setCardstoUser/{userId}")
	  public ResponseEntity<Map<String,Object>> setCardsToUser(@RequestBody Map<String,List<Long>> cardIdsMap, @PathVariable("userId") Long userId){
		  if(cardIdsMap.containsKey("cardIds")) {
			  return new ResponseEntity<>(userService.setCardsToUser(cardIdsMap, userId), HttpStatus.OK);
		  }
		  else{
			  Map<String,Object> map = new HashMap<>();
			  map.put("message","Bad Request");
			  return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		  }
	  }

	}


