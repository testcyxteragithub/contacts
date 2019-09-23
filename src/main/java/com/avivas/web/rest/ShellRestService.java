package com.avivas.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.avivas.service.ShellService;
import com.avivas.service.shell.ShellException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ShellRestService {
	
	@Autowired
	private ShellService shellService;

	@ApiOperation(value = "Servicio que ejecuta comandos")
	@PostMapping(value = "/shell", produces = "text/plain" , consumes = "text/plain")
	@ApiResponses(
	{ 
			@ApiResponse(code = 200, message = "Si los datos son procesados correctamente")
           ,@ApiResponse(code = 500, message = "Sucede si falla al al procesar la peticion")
           ,@ApiResponse(code = 400, message = "Si se envia una peticion invalida")
    })
	ResponseEntity<String> shell(@RequestBody(required = true)
						        String commands) throws ShellException
	{
		String result = getShellService().execute(commands);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	public void setShellService(ShellService shellService) {
		this.shellService = shellService;
	}
	
	public ShellService getShellService() {
		return shellService;
	}
}
