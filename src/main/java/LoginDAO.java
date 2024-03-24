import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
	 // JDBC URL, username, and password for MySQL connection
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    private Connection connection;

    // Constructor to establish database connection
    public LoginDAO() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to validate user's credentials against the MySQL database and retrieve the user's role
    public String validateUserAndGetRole(String email, String password) {
        String role = "";
        try {
            String sql = "SELECT role FROM users WHERE email = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, password);
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        role = result.getString("role"); // Return the user's role if credentials are valid
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role; // Return empty string if credentials are invalid or user's role is not found
    }

    // Close the database connection
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
