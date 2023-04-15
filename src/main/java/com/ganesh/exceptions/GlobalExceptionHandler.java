package com.ganesh.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ganesh.payloads.ApiDataResponse;

public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiDataResponse> resourceNotFoundExceptionMethodHandler(ResourceNotFoundException rex) {
		String mess = rex.getLocalizedMessage();
		System.out.println("resource Not found message: "+mess);
		ApiDataResponse apiDataResponse =new ApiDataResponse(mess,false);
		return new ResponseEntity<ApiDataResponse>(apiDataResponse,HttpStatus.NOT_FOUND);
	}

}
