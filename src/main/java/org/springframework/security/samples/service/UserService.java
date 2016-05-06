package org.springframework.security.samples.service;

import java.util.List;

import org.springframework.security.samples.vo.RoleVO;
import org.springframework.security.samples.vo.UserVO;

public interface UserService {
	List<UserVO> getUserList();

	void deleteUser(String username);

	UserVO getUser(String username);

	List<RoleVO> getRoles();

	void update(UserVO userVO);
}
