package com.email.CinqdEmail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.email.CinqdEmail.Entity.CinqdEmailStaging;

@Repository
public interface EmailStagingRepository extends CrudRepository<CinqdEmailStaging, String>  {
	 
	@Query(value="select * from Cinqd_Email_Staging where sender is not null and sender <> '' and recipient is not null and recipient <> '' and status = 'unsent'", nativeQuery=true)
	public List<CinqdEmailStaging> getEmailsToSend(); 
	
	@Query(value="select * from Cinqd_Email_Staging ", nativeQuery=true)
	public List<CinqdEmailStaging> getEmailLog();
	
	@Query(value="select * from Cinqd_Email_Staging ses where ses.business_id = :businessId and ses.message_id IN :ids", nativeQuery=true)
	public List<CinqdEmailStaging> getEmailByIds(@Param("businessId") int businessId, @Param("ids") List<Integer> ids);
}
