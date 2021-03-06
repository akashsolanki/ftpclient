package org.springframework.security.samples.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.samples.config.test.ActiveDirectory;
import org.springframework.security.samples.data.Role;
import org.springframework.security.samples.data.RoleRepository;
import org.springframework.security.samples.data.User;
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
	@Autowired
	private RoleRepository roleRepository;
	
	
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
		if(!user.getUsername().toLowerCase().equals("super"))
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

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		userRepository.delete(userRepository.findByUsername(username));
	}

	@Override
	public UserVO getUser(String username) {
		org.springframework.security.samples.data.User user = userRepository.findByUsername(username);
		UserVO userVo = new UserVO(user.getUsername());
		Iterator<UserRole> it = userRoleRepository.findByUsername(user.getUsername().toLowerCase()).iterator();
        while(it.hasNext())
        {
        	UserRole roleMap = it.next();
            userVo.getRoles().add(new RoleVO(roleMap.getRoleId(),userRoleRepository.roleByRoleID(roleMap.getRoleId())));
        }
        return userVo;
	}

	@Override
	public List<RoleVO> getRoles() {
		List<RoleVO> roleVOs = new ArrayList();
		Iterator<Role> roles = roleRepository.findAll().iterator();
		
		 while(roles.hasNext())
	        {
			 Role role = roles.next();
			 RoleVO roleVO = new RoleVO(role.getId(),role.getRole());
			 roleVOs.add(roleVO);
		    }
		 return roleVOs;
	}

	@Override
	public void update(UserVO userVO) {
		// TODO Auto-generated method stub
		org.springframework.security.samples.data.User user = userRepository.findByUsername(userVO.getUsername());
		userRoleRepository.deleteByUsername(user.getUsername());
		for(RoleVO role :userVO.getRoles())
		{
			UserRole userrole = new UserRole();
			userrole.setCreated(Calendar.getInstance());
			userrole.setRoleId(role.getRoleId());
			userrole.setUsername(user.getUsername());
			userRoleRepository.save(userrole);
		}
	}

	@Override
	public String create(UserVO userVO) {
		User user = new User();
		user.setUsername(userVO.getUsername());
		String password = RandomStringUtils.random(8, true, true);
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		String hashedPassword = bCryptEncoder.encode(password);
		user.setPassword(hashedPassword);
		user.setCreated(Calendar.getInstance());
		org.springframework.security.samples.data.User savedUser = userRepository.save(user);
		
		for(RoleVO role :userVO.getRoles())
		{
			UserRole userrole = new UserRole();
			userrole.setCreated(Calendar.getInstance());
			userrole.setRoleId(role.getRoleId());
			userrole.setUsername(savedUser.getUsername());
			userRoleRepository.save(userrole);
		}
		
		return password;
	}

	@Override
	public String reset(UserVO userVO) {
		
		org.springframework.security.samples.data.User user = userRepository.findByUsername(userVO.getUsername());
		
		String password = RandomStringUtils.random(8, true, true);
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		String hashedPassword = bCryptEncoder.encode(password);
		user.setPassword(hashedPassword);
		userRepository.save(user);
		return password;
	}
	
	
}
