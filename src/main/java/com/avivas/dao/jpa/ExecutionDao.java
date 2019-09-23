package com.avivas.dao.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avivas.dto.entity.Execution;

@Repository
public interface ExecutionDao extends JpaRepository<Execution, Long> {

}
