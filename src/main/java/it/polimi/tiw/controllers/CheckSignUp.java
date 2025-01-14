package it.polimi.tiw.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;

import it.polimi.tiw.dao.UserDAO;
import it.polimi.tiw.utils.ConnectionHandler;

@WebServlet("/CheckSignUp")
@MultipartConfig
public class CheckSignUp extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Connection connection;
	
	
	public void init() {
		ServletContext context = getServletContext();
		
		try {
			connection = ConnectionHandler.getConnection(context);
		} catch (UnavailableException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException,IOException{
		doPost(request , response);
	}
	
	protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException,IOException{
		String username = StringEscapeUtils.escapeJava(request.getParameter("user"));
		String password = StringEscapeUtils.escapeJava(request.getParameter("password"));
		
		String error = "";
		boolean result = false;
		
		System.out.println("Server is processing the registration request");
		
		//check if the parameters are not empty or null
		if(username == null || password == null || username.isEmpty() || password.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);//Code 400
			response.getWriter().println("Missing parameters;");
			return;
		}
		
		//Check if the userName is too long
		if(username.length() > 45)
			error += "UserName too long;";
		
		//Check if the password is too long
		if(password.length() > 45)
			error += "Password too long;";
		
		if (!error.equals("")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);//Code 400
			response.getWriter().println(error);
			return;
		}
		
		UserDAO userDao = new UserDAO(connection);
		
		try {
			result = userDao.addUser(username, password);
			
			if(result == true) {
				response.setStatus(HttpServletResponse.SC_OK);//Code 200
			}
			else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);//Code 400
				response.getWriter().println("Username not availabe");
			}
		}catch(SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);//Code 500
			response.getWriter().println("Internal server error, retry later");
		}	
	}
	
	public void destroy() {
		try {
			ConnectionHandler.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}






