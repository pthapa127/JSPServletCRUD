package com.soft.application.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.soft.application.model.RolePOJO;
import com.soft.application.model.UserPOJO;
import com.soft.application.DAOImpl.RoleDAOImpl;
import com.soft.application.DAOImpl.UserDAOImpl;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private UserPOJO userInfo;
	private UserDAOImpl userDAO;
	private RoleDAOImpl roleDAO;
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
        userInfo=new UserPOJO();
        userDAO=new UserDAOImpl();
        roleDAO=new RoleDAOImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String path=request.getServletPath();  //getting servlet path
		switch (path) {
		case "/addUsers":
			addUsers(request, response);  //loading add user form
			break;
		case "/viewUsers": 
			listUsers(request, response); //viewing user
			break;
		case "/addUser":
			addUser(request, response);  //adding user
			break;
		case "/updateUserForm":
			getUserById(request, response);  //getting user by id
			break;
		case "/updateUser":
			updateUser(request, response); //updating user
			break;
		case "/deleteUser":
			deleteUser(request, response); //removing user
			break;
		case "/login":
			doLogin(request, response);  //logging user to the system
			break;
		case "/logout":
			logout(request, response);
			break;
		case "/register":
			registerUser(request,response);
			break;
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void registerUser(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		int max=1000000;
		int min=1;
		Random random=new Random();
		int userId=min+random.nextInt(max); //assigning random id to the user
		String firstname=request.getParameter("firstname");
		String middlename=request.getParameter("middlename");
		String lastname=request.getParameter("lastname");
		String address=request.getParameter("address");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String rolename=request.getParameter("role");
		int roleId=roleDAO.getIdByRoleName(rolename);
		
		//setting values to model class
		userInfo.setId(userId);
		userInfo.setFirstname(firstname);
		userInfo.setMiddlename(middlename);
		userInfo.setLastname(lastname);
		userInfo.setAddress(address);
		userInfo.setUsername(username);
		userInfo.setPassword(password);
		
		
		userDAO.saveUsers(userInfo,roleId); //saving user
		response.sendRedirect("registerSuccess.jsp"); //loading success page
	}
	
	//loading add user form
	private void addUsers(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		List<RolePOJO> roles=roleDAO.getAllRoles(); //getting all the roles from database role table
		request.setAttribute("rolesList", roles);  //setting all roles to the add user form
		request.getRequestDispatcher("addUsers.jsp").forward(request, response); //load addusers.jsp form
	}
	
	private void listUsers(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		List<UserPOJO> users=userDAO.listAllUsers(); //getting list of all users
		request.setAttribute("usersList", users);
		request.getRequestDispatcher("viewUsers.jsp").forward(request, response); //loading viewUsers.jsp form
	}
	
	private void addUser(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		//getting value from jsp form
		int id=Integer.parseInt(request.getParameter("id"));
		String firstname=request.getParameter("firstname");
		String middlename=request.getParameter("middlename");
		String lastname=request.getParameter("lastname");
		String address=request.getParameter("address");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String rolename=request.getParameter("role");
		int roleId=roleDAO.getIdByRoleName(rolename);
		
		//setting values to model class
		userInfo.setId(id);
		userInfo.setFirstname(firstname);
		userInfo.setMiddlename(middlename);
		userInfo.setLastname(lastname);
		userInfo.setAddress(address);
		userInfo.setUsername(username);
		userInfo.setPassword(password);
		
		
		userDAO.saveUsers(userInfo,roleId); //saving user
		response.sendRedirect("viewUsers"); //loading viewusers page
	}
	
	private void getUserById(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		int id=Integer.parseInt(request.getParameter("id"));//getting id form url
		UserPOJO userInfo=userDAO.getUserById(id);//getting user by id
		request.setAttribute("userInfo", userInfo);
		List<RolePOJO> roles=roleDAO.getAllRoles(); //getting role
		request.setAttribute("rolesList", roles);//setting role to the jsp form
		request.getRequestDispatcher("updateUserForm.jsp").forward(request, response); //loading updateUserForm.jsp form
	}
	
	private void updateUser(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		//getting value from the jsp form
		int id=Integer.parseInt(request.getParameter("id"));
		String firstname=request.getParameter("firstname");
		String middlename=request.getParameter("middlename");
		String lastname=request.getParameter("lastname");
		String address=request.getParameter("address");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String roleName=request.getParameter("role");
		
		//getting oid
		int user_oid=userDAO.getOidByUserId(id);
		
		//getting roleid
		int roleId=roleDAO.getIdByRoleName(roleName);
		
		
		UserPOJO user=new UserPOJO();
		
		//setting all the values to the model class
		user.setId(id);
		user.setFirstname(firstname);
		user.setMiddlename(middlename);
		user.setLastname(lastname);
		user.setAddress(address);
		user.setUsername(username);
		user.setPassword(password);
		
		//updating user by using model class user,user oid and roleId
		userDAO.updateUser(user, user_oid, roleId);
		response.sendRedirect("viewUsers");
	}
	
	//removing user
	private void deleteUser(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		userDAO.removeUser(id);
		response.sendRedirect("viewUsers");
	}
	
	//login method
	private void doLogin(HttpServletRequest request,HttpServletResponse response)throws ServletException,
	IOException{
		String username=request.getParameter("username");  //getting username
		String password=request.getParameter("password");  //getting password
		UserPOJO userInfo=new UserPOJO();  //model class
		userInfo.setUsername(username); //setting username value to model class
		userInfo.setPassword(password);  //setting password value to model class
		HttpSession session=request.getSession();
		session.setAttribute("username", username);
		
		String checkLogin=userDAO.doLogin(userInfo); //checking login
		if(checkLogin.equalsIgnoreCase("Admin")){  // checking admin role
			request.getRequestDispatcher("/adminDashboard.jsp").forward(request, response);
			
		}else if(checkLogin.equalsIgnoreCase("User")){  //checking user role
			request.getRequestDispatcher("/userDashboard.jsp").forward(request, response);
			
		}
	}
	
	private void logout(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session=request.getSession();
		session.removeAttribute("usernmae");
		response.sendRedirect("index.jsp");
	}
}
