package com.castoldi.custaddr.rest;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomersAddressesExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> databaseConstraintViolation(ConstraintViolationException exception) {
		logger.error("Data integrity issue when inserting data.", exception);

		if (exception.getConstraintName().contains("insert into customer")) {
			return new ResponseEntity<>("The document id must be unique.", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("Constraint error when saving data to the database.", HttpStatus.BAD_REQUEST);
		}
	}

	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	public ResponseEntity<String> handleValidationError(javax.validation.ConstraintViolationException exception) {
		logger.error("Validation error.", exception);

		StringBuilder sb = new StringBuilder();

		Set<ConstraintViolation<?>> validations = exception.getConstraintViolations();
		for (ConstraintViolation<?> validation : validations) {
			sb.append(validation.getPropertyPath() + " " + validation.getMessage());
		}

		return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
	}

}
