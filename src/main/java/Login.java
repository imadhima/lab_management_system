

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// JDBC URL, username, and password for MySQL connection
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get username and password from the login form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate the user's credentials
        String role = validateUserAndGetRole(email, password);

        if (!role.isEmpty()) {
            // Redirect users to their respective pages based on roles
            if (role.equals("admin")) {
                response.sendRedirect("admin/index.jsp");
            } else if (role.equals("patient")) {
                response.sendRedirect("patient/index.jsp");
            } else if (role.equals("lab_assistant")) {
                response.sendRedirect("lab-assistant/index.jsp");
            }
        } else {
        	PrintWriter out = response.getWriter();
            out.println("<script>alert('Your Username or Password is invalid'); window.location.href='login.jsp';</script>");
        }
    }

    // Method to validate user's credentials against the MySQL database and retrieve the user's role
    private String validateUserAndGetRole(String email, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "SELECT role FROM users WHERE email = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, password);
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        return result.getString("role"); // Return the user's role if credentials are valid
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ""; // Return empty string if credentials are invalid or user's role is not found
    }

}
