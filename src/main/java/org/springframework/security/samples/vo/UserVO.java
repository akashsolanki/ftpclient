package org.springframework.security.samples.vo;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

public class UserVO {

	private String username;
	private String commonName;
	private String distinguishedName;
	private List<RoleVO> roles;
	private List<FolderVO> folders;

	
	public UserVO(Attributes attr) throws NamingException {
		  username = (String) attr.get("samaccountname").get();
          commonName = (String) attr.get("cn").get();
          distinguishedName = (String) attr.get("distinguishedName").get();
    }
	public UserVO()
	{
	
	}
	public UserVO(String username) {
		// TODO Auto-generated constructor stub
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getDistinguishedName() {
		return distinguishedName;
	}

	public void setDistinguishedName(String distinguishedName) {
		this.distinguishedName = distinguishedName;
	}

	public List<RoleVO> getRoles() {
		if(roles == null)
			roles =  new ArrayList<RoleVO>();
		return roles;
	}

	public void setRoles(List<RoleVO> roles) {
		this.roles = roles;
	}
	public List<FolderVO> getFolders() {
		return folders;
	}
	public void setFolders(List<FolderVO> folders) {
		this.folders = folders;
	}

}
