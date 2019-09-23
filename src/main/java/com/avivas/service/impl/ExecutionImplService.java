package com.avivas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avivas.dao.jpa.ExecutionDao;
import com.avivas.dto.entity.Execution;
import com.avivas.service.ExecutionService;


@Service
class ExecutionImplService implements ExecutionService {

	@Autowired
	private ExecutionDao executionDao; 
	
	@Override
	public Execution save(Execution execution) {
		return getExecutionDao().save(execution);
	}

	@Override
	public List<Execution> findAll() {
		return getExecutionDao().findAll();
	}
	
	public void setExecutionDao(ExecutionDao executionDao) {
		this.executionDao = executionDao;
	}
	
	public ExecutionDao getExecutionDao() {
		return executionDao;
	}
}
