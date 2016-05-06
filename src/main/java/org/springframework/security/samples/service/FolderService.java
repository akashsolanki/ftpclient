package org.springframework.security.samples.service;

import java.util.List;

import org.springframework.security.samples.vo.FolderVO;

public interface FolderService {

	FolderVO getFolderTree();

	List<FolderVO> getFoldersByUser(String username);
}
