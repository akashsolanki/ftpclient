package org.springframework.security.samples.data;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;


public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	<S extends UserRole> S save(S entity);
	List<UserRole> findByUsername(String username);
	
	@Query(value = "SELECT role FROM Role WHERE id = ?1", nativeQuery = true)
	String roleByRoleID(Long roleid); 
	
	@Query("Delete FROM UserRole as userrole WHERE userrole.username = ?1")
	@Modifying
	@Transactional
	void deleteByUsername(String username);
/*
	  // Enables the distinct flag for the query
	  List<User> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
	  List<User> findPeopleDistinctByLastnameOrFirstname(String lastname, String firstname);

	  // Enabling ignoring case for an individual property
	  List<User> findByLastnameIgnoreCase(String lastname);
	  // Enabling ignoring case for all suitable properties
	  List<User> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);

	  // Enabling static ORDER BY for a query
	  List<User> findByLastnameOrderByFirstnameAsc(String lastname);
	  List<User> findByLastnameOrderByFirstnameDesc(String lastname);*/
}
