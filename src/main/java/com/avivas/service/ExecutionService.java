package com.avivas.service;

import java.util.List;

import com.avivas.dto.entity.Execution;

public interface ExecutionService {

	Execution save(Execution execution);
	List<Execution> findAll();
}
