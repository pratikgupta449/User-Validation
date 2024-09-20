package com.ty;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		HttpSession session=request.getSession(false);
		
		String email=(String) session.getAttribute("Email");
		
		if(deleteDetail(email)==true) {
			response.sendRedirect("login.jsp?deleteSuccess=success");
			System.out.println("successfully deleted data");
		}
		else {
			response.sendRedirect("home.jsp?deleteFail=fail");
			System.out.println("no data found");
		}
	}
	
	public static boolean deleteDetail(String email) {
		
		String delete="DELETE from emp WHERE email=?";
		  // Taking connection from connectionPage(connectionPool).
        Connection con = ConnectionPage.giveConnection();
        System.out.println("connection provided");

        try {
            PreparedStatement ps = con.prepareStatement(delete);
            ps.setString(1, email);

            // Fetch record from database
             ps.execute();

                // Return the connection to arrayList(connectionPool)
                ConnectionPage.submitConnection(con);

                return true ;
            }
         catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

}
