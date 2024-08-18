package com.siddhant.usermicroservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

	//private ApiResponse apiResponse;
	
	@ExceptionHandler(GlobalException.class)
	public ResponseEntity<Map<String, Object>> handlerResourceNotFound(GlobalException ex){
		
		Map map = new HashMap<>();
		
		map.put("Error Message", ex.getMessage());
		map.put("Success", false);
		map.put("status", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.NOT_FOUND);
		
	}
	
}
