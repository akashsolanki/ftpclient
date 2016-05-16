package org.springframework.security.samples.mvc;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.samples.service.UserService;
import org.springframework.security.samples.vo.ResponseVO;
import org.springframework.security.samples.vo.RoleVO;
import org.springframework.security.samples.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Secured({"ROLE_SUPER","ROLE_ADMIN"})
	@RequestMapping(value="/list",method=RequestMethod.GET)
    public @ResponseBody List<UserVO> getUserList() {
		List<UserVO> userList = userService.getUserList();
		return userList;
    }
	
	@Secured("ROLE_SUPER")
	@RequestMapping(value="/create",method=RequestMethod.POST)
    public @ResponseBody ResponseVO createUser(@RequestBody UserVO userVO) {
		String password = userService.create(userVO);
		ResponseVO response = new ResponseVO(); 
		response.setIsSuccess(true);
		response.setReturnObject(password);
		return response;
    }
	
	@Secured("ROLE_SUPER")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
    public @ResponseBody ResponseVO editUser(@RequestBody UserVO userVO) {
		userService.update(userVO);
		ResponseVO response = new ResponseVO(); 
		response.setIsSuccess(true);
		return response;
    }
	
	@Secured("ROLE_SUPER")
	@RequestMapping(value="/reset",method=RequestMethod.POST)
    public @ResponseBody ResponseVO resetPassword(@RequestBody UserVO userVO) {
		String newPassword = userService.reset(userVO);
		ResponseVO response = new ResponseVO(); 
		response.setIsSuccess(true);
		response.setReturnObject(newPassword);
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
    }
	
}
