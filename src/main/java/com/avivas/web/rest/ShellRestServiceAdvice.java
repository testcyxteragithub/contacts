package com.avivas.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.avivas.service.shell.ShellException;

@RestControllerAdvice(assignableTypes = ShellRestService.class)
@RequestMapping(produces = "text/plain")
public class ShellRestServiceAdvice {

	@ExceptionHandler(ShellException.class)
	public ResponseEntity<String> errorShellException(final ShellException eException) {
		return new ResponseEntity<>(eException.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> errorThrowable(final Throwable eException) {
		return new ResponseEntity<>(eException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
