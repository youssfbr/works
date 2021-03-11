package com.github.youssf.works.api.controller.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.youssf.works.domain.services.exceptions.DatabaseException;
import com.github.youssf.works.domain.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
	
	private MessageSource messageSource;
	
	@Autowired
	public ResourceExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found!");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database Exception!");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ApiErrors> fieldsErrors = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
						
			String field = ((FieldError) error).getField();			
			String messageError = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			fieldsErrors.add(new ApiErrors(field, messageError));			
		}
		
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("One or more fields are invalid!");		
		err.setPath(((ServletWebRequest)request).getRequest().getRequestURI().toString());		
		err.setErrors(fieldsErrors);
	
		return super.handleExceptionInternal(ex, err, headers, status, request);
	}
}
