package com.modefinsever.kafka.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modefinsever.kafka.entity.SmsMesg;

@Repository
public interface SmsMesgRepository extends JpaRepository<SmsMesg, Long> {
	
	
}
