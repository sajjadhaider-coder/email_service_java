package com.email.CinqdEmail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.email.CinqdEmail.Entity.CinqdEmail;

@Repository
public interface EmailRepository extends CrudRepository<CinqdEmail, String>  {
	 
	@Query(value="select * from Cinqd_Email where sender is not null and sender <> '' and recipient is not null and recipient <> '' and status = 'unsent'", nativeQuery=true)
	public List<CinqdEmail> getEmailsToSend(); 
	
	@Query(value="select * from Cinqd_Email ", nativeQuery=true)
	public List<CinqdEmail> getEmailLog();
	
	@Query(value="select * from Cinqd_Email se where se.business_id = :businessId and se.message_id IN :ids", nativeQuery=true)
	public List<CinqdEmail> getEmailByIds(@Param("businessId") int businessId, @Param("ids") List<Integer> ids);
}
