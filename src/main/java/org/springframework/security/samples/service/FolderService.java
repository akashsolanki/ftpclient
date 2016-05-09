package org.springframework.security.samples.service;

import java.util.List;

import org.springframework.security.samples.vo.FolderVO;
import org.springframework.security.samples.vo.UserVO;

public interface FolderService {

	FolderVO getFolderTree();

	List<FolderVO> getFoldersByUser(String username, boolean b);

	void saveUserFile(UserVO userVO);

	void createFolder(String foldername, FolderVO folderVO, String name, String rootfolder);

	void rename(String foldername, FolderVO folderVO, String name, String rootfolder);
}
