package com.soft.application.DAO;

import java.util.List;

import com.soft.application.model.UserPOJO;

public interface UserDAO {

	//method of UserDAO interface to carryout CRUD operation for user
	public List<UserPOJO> listAllUsers();
	public void saveUsers(UserPOJO userInfo,int roleId);
	public UserPOJO getUserById(int userId);
	public void removeUser(int userId);
	public String doLogin(UserPOJO user);
	public void updateUser(UserPOJO userINfo,int user_oid,int role_id);
	public int getOidByUserId(int userId);
}
