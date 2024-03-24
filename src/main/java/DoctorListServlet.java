import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DoctorListServlet")
public class DoctorListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Doctorbean> doctors = new ArrayList<>();

        // JDBC URL, username, and password of MySQL server
        String jdbcURL = "jdbc:mysql://localhost:3306/user_db";
        String dbUser = "root";
        String dbPassword = "your_password";

        // Load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            String sql = "SELECT * FROM doctor";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String specialization = result.getString("specialization");
                int experienceYear = result.getInt("experience_year");
                int slmcId = result.getInt("slmc_id");
                int contactNumber = result.getInt("contact_number");
                String address = result.getString("address");

                Doctorbean doctor = new Doctorbean();
                doctor.setId(id);
                doctor.setName(name);
                doctor.setEmail(email);
                doctor.setSpecialization(specialization);
                doctor.setExperienceYear(experienceYear);
                doctor.setSlmcId(slmcId);
                doctor.setContactNumber(contactNumber);
                doctor.setAddress(address);

                doctors.add(doctor);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        request.setAttribute("doctors", doctors);
        request.getRequestDispatcher("doctor-view.jsp").forward(request, response);
    }
}
