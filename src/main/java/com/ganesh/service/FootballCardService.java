package com.ganesh.service;

import java.io.IOException;
import java.util.List;

import com.ganesh.payloads.FootBallCardDto;
import org.springframework.web.multipart.MultipartFile;

public interface FootballCardService {
	
	List<FootBallCardDto> getAllCards();

	FootBallCardDto addCard(FootBallCardDto footballCardDto);

	String uploadImage(MultipartFile file, String path) throws IOException;
}
