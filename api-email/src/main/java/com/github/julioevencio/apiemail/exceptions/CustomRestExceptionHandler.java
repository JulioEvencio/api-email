package com.github.julioevencio.apiemail.exceptions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomRestExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiEmailMessageError> handlerException(Exception e) {
		ApiEmailMessageError errors = new ApiEmailMessageError("Bad Request", Arrays.asList("Bad request..."));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiEmailMessageError> handlerRuntimeException(RuntimeException e) {
		ApiEmailMessageError errors = new ApiEmailMessageError("Bad Request", Arrays.asList("Bad request..."));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiEmailMessageError> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<String> errors = e.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());

		ApiEmailMessageError error = new ApiEmailMessageError("Invalid data", errors);

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}

	@ExceptionHandler(ApiEmailLoginException.class)
	public ResponseEntity<ApiEmailMessageError> handlerApiEmailLoginException(ApiEmailLoginException e) {
		ApiEmailMessageError errors = new ApiEmailMessageError("Login error", Arrays.asList(e.getMessage()));

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
	}

	@ExceptionHandler(ApiEmailResourceNotFoundException.class)
	public ResponseEntity<ApiEmailMessageError> handlerApiEmailResourceNotFoundException(ApiEmailResourceNotFoundException e) {
		ApiEmailMessageError errors = new ApiEmailMessageError("Resource not found", Arrays.asList(e.getMessage()));

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
	}

	@ExceptionHandler(ApiEmailSQLException.class)
	public ResponseEntity<ApiEmailMessageError> handlerApiEmailSQLException(ApiEmailSQLException e) {
		ApiEmailMessageError errors = new ApiEmailMessageError("Invalid data", Arrays.asList(e.getMessage()));

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors);
	}

}
