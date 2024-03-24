

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 

@WebServlet("/admin/lab-assistant/lab-assistant-create")
public class lab_assistant_create extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String username = request.getParameter("username");
	        String email = request.getParameter("email");
	        String nic = request.getParameter("nic");
	        String password = request.getParameter("password");
	        
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        try {
	            // Load the MySQL driver class
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            
	            // Establish connection to your database
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "");
	            
	            // Prepared statement to prevent SQL injection
	            String query = "INSERT INTO users (username, email, nic, password, role) VALUES (?, ?, ?, ?, 'lab_assistant')";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, email);
	            preparedStatement.setString(3, nic);
	            preparedStatement.setString(4, password);
	            
	            // Execute the query
	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                // User registered successfully, redirect to login.jsp
	                response.sendRedirect("lab-assistant-view.jsp");
	            } else {
	                // Failed to register user
	                response.getWriter().println("Failed to register user.");
	            }
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            // Handle errors gracefully
	            response.getWriter().println("Failed to register user. Please try again later.");
	        } finally {
	            try {
	                if (preparedStatement != null) {
	                    preparedStatement.close();
	                }
	                if (connection != null) {
	                    connection.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
