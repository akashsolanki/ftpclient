package org.springframework.security.samples.mvc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.samples.service.FolderService;
import org.springframework.security.samples.vo.FolderVO;
import org.springframework.security.samples.vo.ResponseVO;
import org.springframework.security.samples.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/folder")
public class FolderController {
	
	@Value("${property.root.path}")
	private String rootfolder;
	
	@Value("${restrict}")
	private String restrictedFileTypes;
	
	@Autowired
	private FolderService folderService;
	
	@Secured({"ROLE_SUPER","ROLE_ADMIN"})
	@RequestMapping(value="/list",method=RequestMethod.GET)
    public @ResponseBody List<FolderVO> getFolderTree() {
		FolderVO folderVO = folderService.getFolderTree();
		List<FolderVO> tree = new ArrayList<FolderVO>(Arrays.asList(folderVO));
		System.out.println("***********************"+rootfolder);
		return tree;
    }
	
	@Secured({"ROLE_SUPER","ROLE_ADMIN"})
	@RequestMapping(value="/userlist/{username}",method=RequestMethod.GET)
    public @ResponseBody List<FolderVO> foldersByUser(@PathVariable String username) {
		List<FolderVO> tree = folderService.getFoldersByUser(username,false);
		return tree;
    }
	
	@Secured({"ROLE_SUPER","ROLE_ADMIN"})
	@RequestMapping(value="/create/{foldername}",method=RequestMethod.POST)
    public@ResponseBody ResponseVO createFolder(@PathVariable String foldername,@RequestBody FolderVO folderVO) {
		folderService.createFolder(foldername,folderVO,SecurityContextHolder.getContext().getAuthentication().getName(),rootfolder);
		ResponseVO response = new ResponseVO(); 
		response.setIsSuccess(true);
		return response;
    }
	
	@Secured({"ROLE_SUPER","ROLE_ADMIN"})
	@RequestMapping(value="/rename/{foldername}",method=RequestMethod.POST)
    public@ResponseBody ResponseVO rename(@PathVariable String foldername,@RequestBody FolderVO folderVO) {
		folderService.rename(foldername,folderVO,SecurityContextHolder.getContext().getAuthentication().getName(),rootfolder);
		ResponseVO response = new ResponseVO(); 
		response.setIsSuccess(true);
		return response;
    }
	
	@Secured({"ROLE_SUPER","ROLE_ADMIN"})
	@RequestMapping(value="/delete",method=RequestMethod.POST)
    public@ResponseBody ResponseVO delete(@RequestBody FolderVO folderVO) {
		folderService.delete(folderVO,SecurityContextHolder.getContext().getAuthentication().getName(),rootfolder);
		ResponseVO response = new ResponseVO(); 
		response.setIsSuccess(true);
		return response;
    }
	
	@Secured({"ROLE_SUPER","ROLE_ADMIN"})
	@RequestMapping(value="/download/{folderId}",method=RequestMethod.GET)
    public void download(HttpServletResponse response,@PathVariable Long folderId) {
		//folderService.delete(foldername,folderVO,SecurityContextHolder.getContext().getAuthentication().getName(),rootfolder);
		 File file = null;
         
	        file = folderService.getFile(folderId,SecurityContextHolder.getContext().getAuthentication().getName(),rootfolder);
	         
	        if(!file.exists()){
	            String errorMessage = "Sorry. The file you are looking for does not exist";
	            System.out.println(errorMessage);
	           
	            try {
	            	OutputStream outputStream = response.getOutputStream();
					outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	        }
	         
	        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
	        if(mimeType==null){
	            System.out.println("mimetype is not detectable, will take default");
	            mimeType = "application/octet-stream";
	        }
	         
	        System.out.println("mimetype : "+mimeType);
	         
	        response.setContentType(mimeType);
	         
	        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
	            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
	        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
	 
	         
	        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
	        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
	         
	        response.setContentLength((int)file.length());
	 
	        
	 
	        //Copy bytes from source to destination(outputstream in this example), closes both streams.
	        try {
	        	InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
				FileCopyUtils.copy(inputStream, response.getOutputStream());
				if(file.getName().contains("zip"))
				{
					file.delete();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value="/folderlist",method=RequestMethod.GET)
    public @ResponseBody List<FolderVO> getPermittedFolder() {
		String username  = SecurityContextHolder.getContext().getAuthentication().getName();
		List<FolderVO> tree = new ArrayList();
		if(username !=null)
		  tree = folderService.getFoldersByUser(username,true);
		return tree;
    }
	
	@Secured({"ROLE_SUPER","ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody void UploadFile(@RequestParam("file") MultipartFile[] files,@RequestParam("folderId") Long folderId,
			   RedirectAttributes redirectAttributes) {
		String username  = SecurityContextHolder.getContext().getAuthentication().getName();
		if(username !=null && verifyTypes(files))
		 folderService.saveFiles(files,folderId,username,rootfolder);
    }
	
	
	private boolean verifyTypes(MultipartFile[] files) {
		for(MultipartFile file:files)
		{
			String name = file.getOriginalFilename();
			if(restrictedFileTypes.toLowerCase().contains(name.substring(name.lastIndexOf("."), name.length()).toLowerCase()))
			{
				return false;
			}
		}
		return true;
	}

	@Secured({"ROLE_SUPER","ROLE_ADMIN"})
	@RequestMapping(value="/saveUserFile",method=RequestMethod.POST)
    public  @ResponseBody ResponseVO saveUserFile(@RequestBody UserVO userVO) {
		folderService.saveUserFile(userVO);
		ResponseVO response = new ResponseVO(); 
		response.setIsSuccess(true);
		return response;
    }
	
	/*
	@Secured("ROLE_SUPER")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
    public @ResponseBody ResponseVO editUser(@RequestBody UserVO userVO) {
		userService.update(userVO);
		ResponseVO response = new ResponseVO(); 
		response.setIsSuccess(true);
		return response;
    }
	
	@Secured("ROLE_SUPER")
	@RequestMapping(value="/get/{username}",method=RequestMethod.GET)
    public @ResponseBody UserVO getUser(@PathVariable String username) {
		UserVO user = userService.getUser(username);
		return user;
    }
	
	@Secured("ROLE_SUPER")
	@RequestMapping(value="/roles/list",method=RequestMethod.GET)
    public @ResponseBody List<RoleVO> getRoles() {
		List<RoleVO> roles = userService.getRoles();
		return roles;
    }
	
	@Secured("ROLE_SUPER")
	@RequestMapping(value="/delete/{username}",method=RequestMethod.POST)
    public @ResponseBody ResponseVO deleteUser(@PathVariable String username) {
		userService.deleteUser(username);
		ResponseVO response = new ResponseVO(); 
		response.setIsSuccess(true);
		return response;
    }*/
}
