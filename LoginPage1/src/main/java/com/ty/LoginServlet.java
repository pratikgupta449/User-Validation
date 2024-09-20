package com.ty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		//taking data from login.jsp file
		String email=request.getParameter("email");
		String password=request.getParameter("password");

		System.out.println("LoginServlet "+email+" "+password);
		
		if(checkValidation(email, password)==true) {
			response.sendRedirect("home.jsp");
			System.out.println("successfully matches data");
		}
		else {
			response.sendRedirect("login.jsp?loginFail=fail");
			System.out.println("invalid username and password");
		}
		
		//creating session to print details of the users.
		//and sending data from LoginServlet.java to 
		HttpSession session=request.getSession();
		session.setAttribute("Email", email);

	}

	//creating method to take data from database and checking weather data match or not
	public static boolean checkValidation(String email,String password) {

		String select="select * from emp where email=? and password=?";

		//taking connection from connectionPage(connectionPool).
		Connection con=ConnectionPage.giveConnection();
		System.out.println("connection provided");

		//user provided data we take and from login.jsp and that data we try find exact data from database.
		try {

			PreparedStatement ps=con.prepareStatement(select);

			ps.setString(1,email);
			ps.setString(2,password);

			//fetch record from database
			ResultSet resultFromDatabase=ps.executeQuery();

			// Iterate each and every email and password which is fetched from database and matches with given user data.
	        while (resultFromDatabase.next()) {
	            String fetchEmail = resultFromDatabase.getString("email");
	            String fetchPassword = resultFromDatabase.getString("password");

	            System.out.println(fetchEmail + " " + fetchPassword);

	            // If data matches, return true
	            if (fetchEmail.equals(email) && fetchPassword.equals(password)) {
	                return true;
	            }
	        }
	        System.out.println("data not found");
	        
	      //return the connection to arrayList(connectionPool)
			ConnectionPage.submitConnection(con);
	        // If data does not match, return false
	        return false;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // If an exception occurs, return false
	        return false;
		}


	}

}
