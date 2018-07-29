package com.soft.application.model;

import java.util.ArrayList;
import java.util.List;

//role model class
public class RolePOJO {

	private int roleId;
	private String roleName;
	private List<UserPOJO> users=new ArrayList<>();
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<UserPOJO> getUsers() {
		return users;
	}
	public void setUsers(List<UserPOJO> users) {
		this.users = users;
	}
	
	
}
