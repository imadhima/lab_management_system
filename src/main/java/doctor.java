

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


@WebServlet("/admin/doctor/doctor-create")
public class doctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String specialization = request.getParameter("specialization");
        String experience_yearStr = request.getParameter("experience_year");
        String slmcIdStr = request.getParameter("slmc_id");
        String contactNumberStr = request.getParameter("contact_number");
        String address = request.getParameter("address");

        // Check for null or empty values and handle them appropriately
        int experience_year = (experience_yearStr != null && !experience_yearStr.isEmpty()) ? Integer.parseInt(experience_yearStr) : 0;
        int slmcId = (slmcIdStr != null && !slmcIdStr.isEmpty()) ? Integer.parseInt(slmcIdStr) : 0;
        int contactNumber = (contactNumberStr != null && !contactNumberStr.isEmpty()) ? Integer.parseInt(contactNumberStr) : 0;
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "");

            String query = "INSERT INTO doctor (name, email, specialization, experience_year, slmc_id, contact_number, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, specialization);
            preparedStatement.setInt(4, experience_year);
            preparedStatement.setInt(5, slmcId);
            preparedStatement.setInt(6, contactNumber);
            preparedStatement.setString(7, address);
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                response.sendRedirect("doctor-view.jsp");
            } else {
                response.getWriter().println("Failed to register lab assistant.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Failed to register lab assistant. Please try again later.");
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