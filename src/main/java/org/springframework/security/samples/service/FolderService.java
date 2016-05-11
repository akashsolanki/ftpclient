package org.springframework.security.samples.service;

import java.io.File;
import java.util.List;

import org.springframework.security.samples.vo.FolderVO;
import org.springframework.security.samples.vo.UserVO;
import org.springframework.web.multipart.MultipartFile;

public interface FolderService {

	FolderVO getFolderTree();

	List<FolderVO> getFoldersByUser(String username, boolean b);

	void saveUserFile(UserVO userVO);

	void createFolder(String foldername, FolderVO folderVO, String name, String rootfolder);

	void rename(String foldername, FolderVO folderVO, String name, String rootfolder);

	void saveFiles(MultipartFile[] files, Long folderId, String username, String rootfolder);

	void delete(String foldername, FolderVO folderVO, String name, String rootfolder);

	File getFile(Long folderId, String name, String rootfolder);
}
