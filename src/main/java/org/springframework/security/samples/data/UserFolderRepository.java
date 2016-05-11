package org.springframework.security.samples.data;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

public interface UserFolderRepository extends CrudRepository<UserFolder, Long> {

	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	<S extends UserFolder> S save(S entity);
	List<UserFolder> findByUsername(String username);
	
	@Query("Delete FROM UserFolder as userfolder WHERE userfolder.username = ?1")
	@Modifying
	@Transactional
	void deleteByUsername(String username);
	
	@Query("Delete FROM UserFolder as userfolder WHERE userfolder.folderId = ?1")
	@Modifying
	@Transactional
	void deleteByFolderId(Long folderId);
	
	
	List<UserFolder> findByFolderIdAndUsernameAllIgnoreCase(long folderId, String username);
}
