package org.springframework.security.samples.service;

import java.util.List;

import org.springframework.security.samples.vo.UserVO;

public interface UserService {
	List<UserVO> getUserList();
}
