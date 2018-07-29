package com.soft.application.DAO;

import java.util.List;

import com.soft.application.model.RolePOJO;

public interface RoleDAO {

	
	public List<RolePOJO> getAllRoles();
	public int getIdByRoleName(String roleName);
}
