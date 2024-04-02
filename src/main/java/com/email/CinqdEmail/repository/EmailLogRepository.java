package com.email.CinqdEmail.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.email.CinqdEmail.Entity.CinqdEmailLog;

@Repository
public interface EmailLogRepository extends CrudRepository<CinqdEmailLog, Long> {

}
