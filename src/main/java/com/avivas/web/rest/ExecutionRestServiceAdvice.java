package com.avivas.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ExecutionRestService.class)
@RequestMapping(produces = "text/plain")
public class ExecutionRestServiceAdvice {

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> errorThrowable(final Throwable eException) {
		return new ResponseEntity<>(eException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
