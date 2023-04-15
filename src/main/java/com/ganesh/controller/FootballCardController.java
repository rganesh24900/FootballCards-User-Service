package com.ganesh.controller;

import java.io.IOException;
import java.util.List;

import com.ganesh.payloads.ApiDataResponse;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ganesh.payloads.FootBallCardDto;
import com.ganesh.service.FootballCardService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/cards")
public class FootballCardController {


	private static final Logger log = LoggerFactory.getLogger(FootballCardController.class);

	@Value("${project.player-image}")
	String path;

	@Autowired
	FootballCardService footballCardService;
	
	@GetMapping("/getAllCards")
	public ResponseEntity<List<FootBallCardDto>> getAllCards(){
		return new ResponseEntity<>(footballCardService.getAllCards(),HttpStatus.OK);
	}

	@PostMapping("/createFootballCard")
	public ResponseEntity<ApiDataResponse> addCard(@RequestBody FootBallCardDto footBallCardDto) {
		return new ResponseEntity<>(new ApiDataResponse(true, "Card created successfully", footballCardService.addCard(footBallCardDto)), HttpStatus.CREATED);
	}

	@PostMapping("/uploadImage")
	public ResponseEntity<ApiDataResponse> uploadImage( @RequestBody MultipartFile file) {
		try {
			return  new ResponseEntity<>(new ApiDataResponse(true,"Image uploaded successfully",footballCardService.uploadImage(file , path)),HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ApiDataResponse(false,"Failed to upload Image",e),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
