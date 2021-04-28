package com.productCatalog.exception;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.productCatalog.models.dto.ExceptionResponse;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	private String VAZIO = " ";
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ExceptionResponse handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    StringBuilder errors = new StringBuilder();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	    	errors.append(VAZIO + ((FieldError) error).getField() + VAZIO);
	    	errors.append(error.getDefaultMessage());
	    });
	    return new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), errors);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public void handleNotFoundExceptions(
			NotFoundException ex) {
	}
}
