package com.avivas.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avivas.dto.entity.Execution;
import com.avivas.service.ExecutionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ExecutionRestService {
	
	@Autowired
	private ExecutionService executionService;

	@ApiOperation(value = "Servicio que despliega ejecuciones")
	@GetMapping(value = "/executions", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(
	{ 
			@ApiResponse(code = 200, message = "Si los datos son procesados correctamente")
           ,@ApiResponse(code = 500, message = "Sucede si falla al al procesar la peticion")
    })
	ResponseEntity<List<Execution>> findAll()
	{
		List<Execution> result = getExecutionService().findAll();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}
	
	public ExecutionService getExecutionService() {
		return executionService;
	}
}
