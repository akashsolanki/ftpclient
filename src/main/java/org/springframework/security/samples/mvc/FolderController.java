package org.springframework.security.samples.mvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.samples.service.FolderService;
import org.springframework.security.samples.vo.FolderVO;
import org.springframework.security.samples.vo.ResponseVO;
import org.springframework.security.samples.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/folder")
public class FolderController {
	
	@Autowired
	private FolderService folderService;
	
	@Secured({"ROLE_SUPER","ROLE_ADMIN"})
	@RequestMapping(value="/list",method=RequestMethod.GET)
    public @ResponseBody List<FolderVO> getFolderTree() {
		FolderVO folderVO = folderService.getFolderTree();
		List<FolderVO> tree = new ArrayList<FolderVO>(Arrays.asList(folderVO));
		return tree;
    }
	
	@Secured({"ROLE_SUPER","ROLE_ADMIN"})
	@RequestMapping(value="/userlist/{username}",method=RequestMethod.GET)
    public @ResponseBody List<FolderVO> foldersByUser(@PathVariable String username) {
		List<FolderVO> tree = folderService.getFoldersByUser(username,false);
		return tree;
    }
	
	
	@RequestMapping(value="/folderlist",method=RequestMethod.GET)
    public @ResponseBody List<FolderVO> getPermittedFolder() {
		String username  = SecurityContextHolder.getContext().getAuthentication().getName();
		List<FolderVO> tree = new ArrayList();
		if(username !=null)
		  tree = folderService.getFoldersByUser(username,true);
		return tree;
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
