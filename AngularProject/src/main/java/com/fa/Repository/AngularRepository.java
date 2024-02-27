package com.fa.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fa.Entity.AngularEntity;


@Repository
public interface AngularRepository extends JpaRepository<AngularEntity,Long> {

	@Query(value="select * from angularform where id=?1",nativeQuery = true)
	AngularEntity getDataById(Long id);
	
	@Query(value="select id from angularform where name=?1",nativeQuery = true)
	Long getIdByName(String name);
	
	@Query(value="select email from angularform where email=?1",nativeQuery=true)
	String getEmailvalue(String email);
	
	@Query(value="select password from angularform where email=?1",nativeQuery=true)
	String getPasswordvalue(String email);

	@Query(value="Select * from angularform where id=?1",nativeQuery=true)
	AngularEntity getEditpage(Long id);
	
	AngularEntity findByEmail(String email);

	@Query(value="select * from angularform where email=?1",nativeQuery = true)
	AngularEntity singleData(String email	);

	
//	@Query(value="call data.emp_seq_code()",nativeQuery=true)
//	String getEmpId();
	
	
	
}
