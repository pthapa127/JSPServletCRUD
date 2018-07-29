package com.soft.application.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.matisse.MtDatabase;
import com.matisse.reflect.MtObject;
import com.soft.application.model.Role;
import com.soft.application.model.RolePOJO;
import com.soft.application.model.User;
import com.soft.application.model.UserPOJO;
import com.soft.application.utility.PasswordSecurity;
import com.soft.application.DAO.UserDAO;
import com.soft.application.configuration.DatabaseConfiguration;

public class UserDAOImpl implements UserDAO{

	//private MtDatabase db=DatabaseConfiguration.getConnection();
	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement pstat;
	
	//method to list all the users from database
	@Override
	public List<UserPOJO> listAllUsers() {
		// TODO Auto-generated method stub
		//db.open();
		//db.startTransaction();
		MtDatabase db=DatabaseConfiguration.getConnection();  //getting connection
		connection=db.getJDBCConnection(); //getting jdbc connection
		db.startTransaction(); //starting transaction
		List<UserPOJO> users=new ArrayList<>();
		String cmd="SELECT u.id,u.firstname,u.middlename,u.lastname,u.address,"
				+ "u.username,u.password,r.roleName FROM User u,Role r";
		
		try{
			pstat=connection.prepareStatement(cmd);
			resultSet=pstat.executeQuery(); //executing query
			while(resultSet.next()){
				UserPOJO userInfo=new UserPOJO();
				RolePOJO roleInfo=new RolePOJO();
				//MtObject mtObj=(MtObject)resultSet.getObject(1);
				//User user=new User(db,mtObj.mtOid);
				userInfo.setId(resultSet.getInt(1));
				userInfo.setFirstname(resultSet.getString(2));
				userInfo.setMiddlename(resultSet.getString(3));
				userInfo.setLastname(resultSet.getString(4));
				userInfo.setAddress(resultSet.getString(5));
				userInfo.setUsername(resultSet.getString(6));
				userInfo.setPassword(resultSet.getString(7));
				String role=resultSet.getString(8);
				roleInfo.setRoleName(role);
				userInfo.setRole(roleInfo);
				//user.setId(user.getId());
				//user.setFirstname(mtObj.getString(User.getFirstnameAttribute(db)));
				//user.setMiddlename(mtObj.getString(User.getMiddlenameAttribute(db)));
				//user.setLastname(mtObj.getString(User.getLastnameAttribute(db)));
				//user.setAddress(mtObj.getString(User.getAddressAttribute(db)));
				//user.setUsername(mtObj.getString(User.getUsernameAttribute(db)));
				//user.setPassword(mtObj.getString(User.getPasswordAttribute(db)));
				users.add(userInfo);
			}
			db.commit();  //commiting database
			db.close(); //closing database
			return users;  //returning list of users
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}

	//method to save user
	@Override
	public void saveUsers(UserPOJO userInfo,int roleId) {
		// TODO Auto-generated method stub
		byte[] salt=PasswordSecurity.getSaltForPasswordSecure();  
		String password=userInfo.getPassword();
		String securePassword=PasswordSecurity.securePassword(password, salt);  //securing password by hashing
		MtDatabase db=DatabaseConfiguration.getConnection(); //getting database connection
		db.startTransaction(); //starting transaction
		User user=new User(db); //user model matisse class
		Role role=new Role(db, roleId); //role model matisse class
		//setting values to the properties to matisse user class by using userInfo model class
		user.setId(userInfo.getId());
		user.setFirstname(userInfo.getFirstname());
		user.setMiddlename(userInfo.getMiddlename());
		user.setLastname(userInfo.getLastname());
		user.setAddress(userInfo.getAddress());
		user.setUsername(userInfo.getUsername());
		user.setPassword(securePassword);
		user.setHasRoles(role);  //setting role to user
		db.commit(); //committing database
		db.close(); //closing database
		
	}
	
	//method to get user by id
	@Override
	public UserPOJO getUserById(int userId) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection(); //getting database connection
		connection=db.getJDBCConnection(); //getting jdbc connection
		db.startTransaction(); //starting transaction
		String cmd="SELECT REF(user) FROM User user where user.id=?"; //query
		UserPOJO userInfo=new UserPOJO();  //model class
		
		try{
			pstat=connection.prepareStatement(cmd);
			pstat.setInt(1, userId);
			resultSet=pstat.executeQuery(); //executing query
			while(resultSet.next()){
				
				MtObject mtObj=(MtObject)resultSet.getObject(1); //accessing mtobject
				User user=new User(db,mtObj.mtOid);
				//setting properties
				userInfo.setId(user.getId());
				userInfo.setFirstname(user.getFirstname());
				userInfo.setMiddlename(user.getMiddlename());
				userInfo.setLastname(user.getLastname());
				userInfo.setAddress(user.getAddress());
				userInfo.setUsername(user.getUsername());
				userInfo.setPassword(user.getPassword());
				
			}
			db.commit(); //committing database
			db.close(); //closing database
			return userInfo;  //returning userInfo on the basis of id
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		return null;
	}

	//method to remove user
	@Override
	public void removeUser(int userId) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection(); //getting database connection
		connection=db.getJDBCConnection();//getting jdbc connection
		db.startTransaction();  //starting transaction
		String cmd="DELETE from User user where user.id=?"; //delete query
		
		try{
			pstat=connection.prepareStatement(cmd);
			pstat.setInt(1, userId);
			pstat.executeUpdate();  //execute 
			db.commit(); //committing database
			db.close();  //closing database
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
	}

	
	//method to login for user
	@Override
	public String doLogin(UserPOJO user) {
		// TODO Auto-generated method stub
		MtDatabase db=DatabaseConfiguration.getConnection();  //getting database
		connection=db.getJDBCConnection();  //getting jdbc connection
		db.startTransaction();  //strating transaction
		String email=user.getUsername();  //getting username/email
		String password=user.getPassword(); //getting password
		byte[] salt=PasswordSecurity.getSaltForPasswordSecure();
		String securePassword=PasswordSecurity.securePassword(password, salt);  //hashing password
		String cmd="SELECT u.username,u.password,r.roleName FROM User u,Role r"; //query
		
		String emailDB="";
		String passwordDB="";
		String roleDB="";
		
		try{
			pstat=connection.prepareStatement(cmd);
			resultSet=pstat.executeQuery(); //executing 
			while(resultSet.next()){
				emailDB=resultSet.getString(1); //getting username/email from db
				passwordDB=resultSet.getString(2); //getting password from db
				roleDB=resultSet.getString(3); //getting role from db
				
				if(email.equalsIgnoreCase(emailDB) && securePassword.equalsIgnoreCase(passwordDB) && 
						roleDB.equalsIgnoreCase("Admin") ){  //check for admin
					db.commit();
					db.close();
					return "Admin";
				}else if(email.equalsIgnoreCase(emailDB) && securePassword.equalsIgnoreCase(passwordDB) && 
						roleDB.equalsIgnoreCase("User")){ //check for user
					db.commit();
					db.close();
					return "User";
				}
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return "Invalid Credentials";  //if login fails
	}

	//this method will update user 
	@Override
	public void updateUser(UserPOJO userInfo, int user_oid,int role_id) {
		// TODO Auto-generated method stub
		byte[] salt=PasswordSecurity.getSaltForPasswordSecure();
		String password=userInfo.getPassword();
		String securePassword=PasswordSecurity.securePassword(password, salt);  //hashing password
		MtDatabase db=DatabaseConfiguration.getConnection(); //getting db connection
		db.startTransaction(); //starting transaction
		User user=new User(db,user_oid);
		Role role=new Role(db, role_id);
		//setting values to update
		user.setId(userInfo.getId());
		user.setFirstname(userInfo.getFirstname());
		user.setMiddlename(userInfo.getMiddlename());
		user.setLastname(userInfo.getLastname());
		user.setAddress(userInfo.getAddress());
		user.setUsername(userInfo.getUsername());
		user.setPassword(securePassword);
		user.setHasRoles(role); //updating role
		db.commit(); //committing database
		db.close();  //closing database
	}

	//method to get mtoid by user id
	@Override
	public int getOidByUserId(int userId) {
		// TODO Auto-generated method stub
		int oid=0;
		String cmd="SELECT REF(user) FROM User user where user.id=?";
		MtDatabase db=DatabaseConfiguration.getConnection();
		connection=db.getJDBCConnection();
		db.startTransaction();
		try{
			pstat=connection.prepareStatement(cmd);
			pstat.setInt(1, userId);
			resultSet=pstat.executeQuery();
			while(resultSet.next()){
				MtObject mtObj=(MtObject) resultSet.getObject(1);
				oid=mtObj.mtOid;
			}
			db.commit();
			db.close();
			return oid;
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		return 0;
	}

}
