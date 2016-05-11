package org.springframework.security.samples.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.samples.data.Folder;
import org.springframework.security.samples.data.FolderRepository;
import org.springframework.security.samples.data.UserFolder;
import org.springframework.security.samples.data.UserFolderRepository;
import org.springframework.security.samples.service.FolderService;
import org.springframework.security.samples.util.ZIPUtil;
import org.springframework.security.samples.vo.FolderVO;
import org.springframework.security.samples.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



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

	@Override
	public void createFolder(String foldername, FolderVO folderVO, String username,  String rootfolder) {
		if(checkFolderPermissions(folderVO.getId(),username))
		{
			File files = new File(rootfolder+"\\"+folderVO.getPath()+"\\"+foldername);
	        if (!files.exists()) {
	            if (files.mkdirs()) {
	            	Folder folder = new Folder();
	            	folder.setIsfolder(true);
	            	folder.setName(foldername);
	            	folder.setParentId(folderVO.getId());
	            	folder.setPath(folderVO.getPath()+"\\"+foldername);
	            	folderRepository.save(folder);
	                System.out.println("Multiple directories are created!");
	            } else {
	                System.out.println("Failed to create multiple directories!");
	            }
	        }
		}
	}

	private boolean checkFolderPermissions(long id, String username) {
		List<UserFolder> list = userFolderRepository.findByFolderIdAndUsernameAllIgnoreCase(id, username);
		if(list.size()>0)
		{
			boolean canwrite = list.get(0).isCanWrite();
			Long pid = folderRepository.findById(id).getParentId();
			if(canwrite)
				return true;
			else if(pid!=null && pid!=0)
			{
				return checkFolderPermissions(pid,username);
			}
		}
		else
		{
			Long pid = folderRepository.findById(id).getParentId();
			if(pid!=null && pid!=0)
			{
				return checkFolderPermissions(pid,username);
			}
		}
		return false;
	}

	@Override
	public void rename(String foldername, FolderVO folderVO, String username, String rootfolder) {
		// TODO Auto-generated method stub
		if(checkFolderPermissions(folderVO.getId(),username))
		{
			File files = new File(rootfolder+"\\"+folderVO.getPath());
			File dest = new File(rootfolder+"\\"+folderVO.getPath().replace(folderVO.getName(), foldername));
	        if (files.exists()) {
	            if (files.renameTo(dest)) {
	            	Folder folder = folderRepository.findById(folderVO.getId());
	            	folder.setName(foldername);
	            	folder.setPath(folderVO.getPath().replace(folderVO.getName(), foldername));
	            	folderRepository.save(folder);
	                System.out.println("Rename Done...!");
	            } else {
	                System.out.println("Failed to Rename!");
	            }
	        }
		}
	}

	@Override
	public void saveFiles(MultipartFile[] files, Long folderId, String username, String rootfolder) {
		// TODO Auto-generated method stub
		if(checkFolderPermissions(folderId,username))
		{
			Folder folder= folderRepository.findById(folderId);
			for(MultipartFile file:files)
			{
				try
				{
					File dest = new File(rootfolder+"\\"+folder.getPath()+"\\"+file.getOriginalFilename());
					file.transferTo(dest);
					Folder nfolder = new Folder();
					nfolder.setIsfolder(false);
					nfolder.setName(file.getOriginalFilename());
					nfolder.setParentId(folderId);
					nfolder.setPath(folder.getPath()+"\\"+file.getOriginalFilename());
	            	folderRepository.save(nfolder);
	                System.out.println("Multiple directories are created!");
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(FolderVO folderVO, String username, String rootfolder) {
		// TODO Auto-generated method stub
		if(checkFolderPermissions(folderVO.getId(),username)&&folderVO.getId()!=1)
		{
			Path path = FileSystems.getDefault().getPath(rootfolder+"\\"+folderVO.getPath());
	        boolean success = false;
			try {
				Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
					   @Override
					   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						   Files.delete(file);
						   return FileVisitResult.CONTINUE;
					   }

					   @Override
					   public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
						   Files.delete(dir);
						   return FileVisitResult.CONTINUE;
					   }});
				deleteFromDB(folderVO.getId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	   }
	}

	private void deleteFromDB(long id) {
		// TODO Auto-generated method stub
		List<Folder> folders = folderRepository.findByParentId(id);
		folderRepository.delete(id);
		userFolderRepository.deleteByFolderId(id);
		for(Folder folder:folders)
		{
			deleteFromDB(folder.getId());
		}
	}

	@Override
	public File getFile(Long folderId, String username, String rootfolder) {
		// TODO Auto-generated method stub
		if(checkFolderPermissions(folderId,username))
		{
			Folder folder = folderRepository.findById(folderId);
			File file = null;
			if(folder.isIsfolder())
				file = ZIPUtil.zipDirectory(new File(rootfolder+"\\"+folder.getPath()), folder.getName()+".zip");
			else
				file = new File(rootfolder+"\\"+folder.getPath());
	        return file;
		}
		return null;
	}
}
