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
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		System.out.println("Update Sevlet");


		//get data from update.jsp page
		String updateName=request.getParameter("name");
		String updatePhone=request.getParameter("phone");
		String email=request.getParameter("email");

		System.out.println("UpdateServlet "+updateName+" "+updatePhone+" "+email);

		//set data to User java Page.
		//creating User class object
		User user=new User();
		user.setName(updateName);
		user.setEmail(email);
		user.setPhone(updatePhone);

		if(updateDetails(user)==true) {
			response.sendRedirect("home.jsp?updateSuccess=success");
			System.out.println("successfully updated data");
		}
		else {
			response.sendRedirect("update.jsp?updateFail=fail");
			System.out.println("invalid username and password");
		}
		

	}

	public static boolean updateDetails(User user) {
	    String Update = "UPDATE emp SET name=?, phone=? WHERE email=?";

	    // Taking connection from connectionPage(connectionPool).
	    Connection con = ConnectionPage.giveConnection();
	    System.out.println("connection provided");

	    try {
	        PreparedStatement ps = con.prepareStatement(Update);

	        ps.setString(1, user.getName());
	        ps.setString(2, user.getPhone());
	        ps.setString(3, user.getEmail());

	        int rowsUpdated = ps.executeUpdate();
	        System.out.println("Details Updated");

	        // Return the connection to arrayList(connectionPool)
	        ConnectionPage.submitConnection(con);

	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}