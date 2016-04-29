package org.springframework.security.samples.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.samples.service.UserService;
import org.springframework.security.samples.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Secured("ROLE_SUPER")
	@RequestMapping(value="/list",method=RequestMethod.GET)
    public @ResponseBody List<UserVO> getUserList() {
		List<UserVO> userList = userService.getUserList();
		return userList;
    }
	@RequestMapping(value="/incheck",method=RequestMethod.GET)
    public String check(Authentication authentication) {
        Object p = authentication.getCredentials();
        Object o =SecurityContextHolder.getContext().getAuthentication();
        return "admin1";
    }
}
