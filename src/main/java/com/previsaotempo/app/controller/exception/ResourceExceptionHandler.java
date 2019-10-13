package com.previsaotempo.app.controller.exception;

import java.time.format.DateTimeParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import com.previsaotempo.app.service.exception.InvalidPeriod;
import com.previsaotempo.app.service.exception.ObjectNotFoundException;

import java.net.UnknownHostException;


@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Objeto não encontrado", e.getMessage(), request.getRequestURI());		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<StandardError> dateTimeInvalid(DateTimeParseException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Data inválida", e.getMessage(), request.getRequestURI());		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(InvalidPeriod.class)
	public ResponseEntity<StandardError> periodInvalid(InvalidPeriod e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Periodo inválido", e.getMessage(), request.getRequestURI());		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(UnknownHostException.class)
	public ResponseEntity<StandardError> internetConnectionDown(UnknownHostException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.REQUEST_TIMEOUT.value(), "Falha de conexão", e.getMessage(), request.getRequestURI());		
		
		return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(err);
	}
	
	@ExceptionHandler(HttpServerErrorException.class)
	public ResponseEntity<StandardError> internalServerError(HttpServerErrorException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Problema com API web externa", e.getMessage(), request.getRequestURI());		
		
		return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(err);
	}

}
