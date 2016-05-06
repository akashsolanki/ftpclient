package org.springframework.security.samples.vo;

public class RoleVO {
 long roleId;
 String roleName;
public RoleVO(long roleId, String roleName) {
	// TODO Auto-generated constructor stub
	this.roleId = roleId;
	this.roleName = roleName; 
}
public RoleVO() {
}
public long getRoleId() {
	return roleId;
}
public void setRoleId(long roleId) {
	this.roleId = roleId;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
 
}
