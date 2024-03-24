import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class lab_assistant_createDAO {
	private Connection connection;

    public lab_assistant_createDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean registerUser(String username, String email, String nic, String password) {
        String query = "INSERT INTO users (username, email, nic, password, role) VALUES (?, ?, ?, ?, 'lab_assistant')"; // Corrected SQL query
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        	 preparedStatement.setString(1, username);
	         preparedStatement.setString(2, email);
	         preparedStatement.setString(3, nic);
	         preparedStatement.setString(4, password);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
