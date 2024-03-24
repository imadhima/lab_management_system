
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

	public class test_typeDAO {
	    private static Connection connection;

	    public test_typeDAO(Connection connection) {
	        this.connection = connection;
	    }

	    public static boolean registerUser(String testName, String description) {
	        String query = "INSERT INTO test_type (test_name, description) VALUES (?, ?)";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, testName);
	            preparedStatement.setString(2, description);
	            int rowsAffected = preparedStatement.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	}
