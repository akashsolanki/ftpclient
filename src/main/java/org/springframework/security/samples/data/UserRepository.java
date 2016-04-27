package org.springframework.security.samples.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {

	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	<S extends User> S save(S entity);
	@Query("FROM User as user WHERE user.username = ?1")
	User findByUsername(String username);
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
