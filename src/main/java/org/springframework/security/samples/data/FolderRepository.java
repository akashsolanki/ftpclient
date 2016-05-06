package org.springframework.security.samples.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

public interface FolderRepository extends CrudRepository<Folder, Long> {

	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	<S extends Folder> S save(S entity);
	Folder findByNameIgnoreCase(String name);
	List<Folder> findByParentId(Long parentId);
}
