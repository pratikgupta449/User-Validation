package com.ty;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class DetailsServlet
 */
public class DetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DetailsServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creating session to print details of the successfully login users.
        HttpSession session = request.getSession(false);

        String email = (String) session.getAttribute("Email");

        String userDetails = printDetails(email);

        // Setting response content type
        response.setContentType("text/html");

        // Printing the user details
        PrintWriter out = response.getWriter();
       
        out.println("<html>");
        out.println("<head><link rel='stylesheet' type='text/css' href='style.css'></head>");
        out.println("<body>");
        out.println("<h1>User Details </h1>");
        out.println("<pre>              </pre>");
        out.println("<p>" + userDetails+ "</p>");
        out.println("<pre>              </pre>");
        out.println("<h1><a href='home.jsp'>Back</a></h1>");
        out.println("</body>");
        out.println("</html>");


    }

    public static String printDetails(String email) {
        String select = "select * from emp where email=?";

        // Taking connection from connectionPage(connectionPool).
        Connection con = ConnectionPage.giveConnection();
        System.out.println("connection provided");

        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setString(1, email);

            // Fetch record from database
            ResultSet detailsFromDB = ps.executeQuery();

            if (detailsFromDB.next()) {
                String nameDB = detailsFromDB.getString(1);
                String emailDB = detailsFromDB.getString(2);
                String phoneDB = detailsFromDB.getString(3);

                System.out.println(nameDB + " " + emailDB + " " + phoneDB);

                // Return the connection to arrayList(connectionPool)
                ConnectionPage.submitConnection(con);

                return nameDB + "  " + emailDB + "  " + phoneDB;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No details found";
    }
}
