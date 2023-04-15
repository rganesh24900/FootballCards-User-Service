package com.ganesh.service.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ganesh.exceptions.ResourceNotFoundException;
import com.ganesh.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganesh.entities.FootballCard;
import com.ganesh.entities.User;
import com.ganesh.payloads.FootBallCardDto;
import com.ganesh.payloads.UserDto;
import com.ganesh.repository.FootballCardRepo;
import com.ganesh.service.FootballCardService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FootBallCardServiceImpl implements FootballCardService {
	
	@Autowired
	FootballCardRepo footballCardRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<FootBallCardDto> getAllCards() {
		return footballCardRepo.findAll().stream().map(this::cardToDto).collect(Collectors.toList());
	}

	public FootBallCardDto addCard(FootBallCardDto footballCardDto) {
		FootballCard footballCard = dtoToCard(footballCardDto);
		System.out.println("Yoo: "+footballCardDto.getDribble()+" "+footballCardDto.getPlayerName());
		footballCardRepo.save(footballCard);
		return cardToDto(footballCard);
	}

	public String uploadImage(MultipartFile file, String path) throws IOException {
        String fileName = file.getOriginalFilename();

		String filePath = path + File.separator + fileName ;

		String randomId = UUID.randomUUID().toString();
		String filePath1 = randomId.concat(filePath.substring(fileName.lastIndexOf(".")));

		File f = new File(path);

		if (!f.exists()) {
			f.mkdir();
		}

		Files.copy(file.getInputStream(), Paths.get(filePath1));

		return filePath1;
	}

	private FootballCard dtoToCard(FootBallCardDto cardDto) {
		return modelMapper.map(cardDto, FootballCard.class);
	}
	
	private FootBallCardDto cardToDto(FootballCard card) {
		return modelMapper.map(card, FootBallCardDto.class);
	}

}
