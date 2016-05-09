package org.springframework.security.samples.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.samples.data.Folder;
import org.springframework.security.samples.data.FolderRepository;
import org.springframework.security.samples.data.UserFolder;
import org.springframework.security.samples.data.UserFolderRepository;
import org.springframework.security.samples.service.FolderService;
import org.springframework.security.samples.vo.FolderVO;
import org.springframework.security.samples.vo.UserVO;
import org.springframework.stereotype.Service;



@Service("folderService")
public class FolderServiceImpl implements FolderService {

	@Autowired
	private FolderRepository folderRepository;
	
	@Autowired
	private UserFolderRepository userFolderRepository;
	
	@Override
	public FolderVO getFolderTree() {
		// TODO Auto-generated method stub
		Folder root = folderRepository.findByNameIgnoreCase("root");
		FolderVO rootVO = new FolderVO();
		copyToVO(root,rootVO);
		buildtree(rootVO);
		return rootVO;
	}

	private void buildtree(FolderVO rootVO) {
		// TODO Auto-generated method stub
		List<Folder> children = folderRepository.findByParentId(rootVO.getId());
		for(Folder folder:children)
		{
			FolderVO childVO = new FolderVO();
			copyToVO(folder,childVO);
			rootVO.getChildren().add(childVO);
			buildtree(childVO);
		}
	}

	private void copyToVO(Folder root, FolderVO rootVO) {
		// TODO Auto-generated method stub
		rootVO.setId(root.getId());
		rootVO.setChildren(new ArrayList<FolderVO>());
		rootVO.setName(root.getName());
		rootVO.setParentId(root.getParentId()==null?0:root.getParentId());
		rootVO.setPath(root.getPath());
		rootVO.setType(root.isIsfolder());
	}

	private List<FolderVO> convertToFolderVO(List<UserFolder> list) {
		// TODO Auto-generated method stub
		List<FolderVO> lfoldervos  = new ArrayList<FolderVO>();
		for(UserFolder userfolder:list)
		{
			FolderVO foldervo = new FolderVO();
			foldervo.setId(userfolder.getFolderId());
			foldervo.setChildren(null);
			foldervo.setRead(userfolder.isCanRead());
			foldervo.setWrite(userfolder.isCanWrite());
			lfoldervos.add(foldervo);
		}
		return lfoldervos;
	}

	@Override
	public void saveUserFile(UserVO userVO) {
		// TODO Auto-generated method stub
		userFolderRepository.deleteByUsername(userVO.getUsername());
		for(FolderVO folderVO:userVO.getFolders())
		{
			UserFolder userFolder = getFolderFromVO(folderVO,userVO.getUsername());
			userFolderRepository.save(userFolder);
		}
	}

	private UserFolder getFolderFromVO(FolderVO folderVO,String username) {
		// TODO Auto-generated method stub
		UserFolder userFolder = new UserFolder();
		userFolder.setCanRead(folderVO.isRead());
		userFolder.setCanWrite(folderVO.isWrite());
		userFolder.setFolderId(folderVO.getId());
		userFolder.setUsername(username);
		return userFolder;
	}

	@Override
	public List<FolderVO> getFoldersByUser(String username, boolean all) {
		// TODO Auto-generated method stub
				List<UserFolder> list = userFolderRepository.findByUsername(username);
				List<FolderVO> foldervos = convertToFolderVO(list);
				if(all)
				for(FolderVO folderVO : foldervos)
				{
					Folder root = folderRepository.findById(folderVO.getId());
					FolderVO rootVO = new FolderVO();
					copyToVO(root,folderVO);
					buildtree(folderVO);
				}
				return foldervos;
	}
	
	
}
