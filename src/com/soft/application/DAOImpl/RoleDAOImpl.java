package com.soft.application.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.matisse.MtDatabase;
import com.matisse.reflect.MtObject;
import com.soft.application.DAO.RoleDAO;
import com.soft.application.configuration.DatabaseConfiguration;
import com.soft.application.model.Role;
import com.soft.application.model.RolePOJO;

public class RoleDAOImpl implements RoleDAO{

	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement pstat;
	
	
	@Override
	public List<RolePOJO> getAllRoles() {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection();
		connection=db.getJDBCConnection();
		db.startTransaction();
		List<RolePOJO> roles=new ArrayList<>();
		String cmd="SELECT REF(role) FROM Role role";
		
		try{
			pstat=connection.prepareStatement(cmd);
			resultSet=pstat.executeQuery();
			while(resultSet.next()){
				RolePOJO roleInfo=new RolePOJO();
				MtObject mtObj=(MtObject) resultSet.getObject(1);
				Role role=new Role(db,mtObj.mtOid);
				roleInfo.setRoleId(role.getRoleId());
				roleInfo.setRoleName(role.getRoleName());
				roles.add(roleInfo);
			}
			db.commit();
			db.close();
			return roles;
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		return null;
	}


	@Override
	public int getIdByRoleName(String roleName) {
		// TODO Auto-generated method stub
		int roleId=0;
		MtDatabase db=DatabaseConfiguration.getConnection();
		connection=db.getJDBCConnection();
		db.startTransaction();
		
		String cmd="SELECT REF(role) FROM Role role where role.roleName=?";
		
		try{
			pstat=connection.prepareStatement(cmd);
			pstat.setString(1, roleName);
			resultSet=pstat.executeQuery();
			while(resultSet.next()){
				MtObject mtObj=(MtObject)resultSet.getObject(1);
				roleId=mtObj.mtOid;
				
			}
			db.commit();
			db.close();
			return roleId;
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		return 0;
	}

}
