package org.springframework.security.samples.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.samples.config.test.ActiveDirectory;
import org.springframework.security.samples.config.test.ActiveDirectory.User;
import org.springframework.security.samples.data.UserRepository;
import org.springframework.security.samples.data.UserRole;
import org.springframework.security.samples.data.UserRoleRepository;
import org.springframework.security.samples.service.UserService;
import org.springframework.security.samples.vo.RoleVO;
import org.springframework.security.samples.vo.UserVO;
import org.springframework.stereotype.Service;



@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public List<UserVO> getUserList() {
		List<UserVO> userList = new ArrayList();
	/*List<UserVO> userList=getListFromAD();
	for(UserVO user:userList)
	{
			Iterator<UserRole> it = userRoleRepository.findByUsername(user.getUsername().toLowerCase()).iterator();
	        while(it.hasNext())
	        {
	            UserRole roleMap = it.next();
	            user.getRoles().add(new RoleVO(roleMap.getRoleId(),userRoleRepository.roleByRoleID(roleMap.getRoleId())));
	        }
	}*/
	Iterable<org.springframework.security.samples.data.User> users = userRepository.findAll();
	for(org.springframework.security.samples.data.User user:users)
	{
		Iterator<UserRole> it = userRoleRepository.findByUsername(user.getUsername().toLowerCase()).iterator();
		UserVO userVo = new UserVO(user.getUsername());
	        while(it.hasNext())
	        {
	        	UserRole roleMap = it.next();
	            userVo.getRoles().add(new RoleVO(roleMap.getRoleId(),userRoleRepository.roleByRoleID(roleMap.getRoleId())));
	        }
	        userList.add(userVo);
	}
	return userList;
	}

	private List<UserVO> getListFromAD() {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<UserVO> userList = new ArrayList();
		try {
			LdapContext conn = ActiveDirectory.getConnection(auth.getName(), auth.getCredentials().toString(),"ggktech.local","ldap://172.16.0.18:389");
			userList = ActiveDirectory.getUsers(conn);
			conn.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

}
