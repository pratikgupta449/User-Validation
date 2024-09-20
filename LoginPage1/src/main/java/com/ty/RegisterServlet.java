package com.ty;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class RegisterServlet
 */


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		//get data from register.jsp page
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String password=request.getParameter("password");

		System.out.println("RegisterServlet "+name+" "+email+" "+phone+" "+password);

		//set data to User java Page.
		//creating User class object
		User user=new User();
		user.setName(name);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);

		//after successfully registration it will redirect to login.jsp page
		if(addUser(user)==true) {
			response.sendRedirect("login.jsp?registerSuccessfully=success");
		}
		else {
			//if it failed it will redirect to register.jsp page. and print this message on register.jsp page.
			response.sendRedirect("register.jsp?registerFailed=fail");
			System.out.println("already username present");
		}

	}

	//creating method to add the user data into database.
	public static boolean addUser(User user) {

		String insert="insert into emp (name,email,phone,password) values (?,?,?,?)";
		 int count=0;
		//taking connection from connectionPage(connectionPool).
		Connection con=ConnectionPage.giveConnection();
		System.out.println("connection provided");

		try {
	
				PreparedStatement ps=con.prepareStatement(insert);
				
				ps.setString(1,user.getName());
				ps.setString(2, user.getEmail());
				ps.setString(3, user.getPhone());
				ps.setString(4, user.getPassword());
				
				ps.execute();
				System.out.println("data inserted");
				count++;

				//return the connection to arrayList(connectionPool)
				ConnectionPage.submitConnection(con);
				System.out.println(count);
				return true;
		}
			
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		
	}




















}
