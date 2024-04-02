package com.email.CinqdEmail.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.email.CinqdEmail.Entity.CinqdEmailLog;
import com.email.CinqdEmail.Entity.CinqdEmailLogStaging;

@Repository
public interface EmailLogStagingRepository extends CrudRepository<CinqdEmailLogStaging, String> {
	
}
