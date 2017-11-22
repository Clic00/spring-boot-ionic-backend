package com.clic00.cursomc.resources.exceptions;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.clic00.cursomc.services.exceptions.DataIntegrityException;
import com.clic00.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	private SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ssss") ;

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new  StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(),dtFormat.format(System.currentTimeMillis()));
		return 	ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityException e,HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),dtFormat.format(System.currentTimeMillis()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
