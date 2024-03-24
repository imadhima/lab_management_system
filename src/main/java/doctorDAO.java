import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;




public class doctorDAO {
	private Connection connection;

    public doctorDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean registerDoctor(String name, String email, String specialization,int experience_year,int slmcId,int contactNumber,  String address) {
        String query = "INSERT INTO `doctor` (`name`, `email`, `specialization`, `experience_year`, `slmc_id`, `contact_number`, `address`) VALUES (?, ?, ?, ?, ?, ?, ?)"; // Corrected SQL query
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, specialization);
            preparedStatement.setInt(4, experience_year);
            preparedStatement.setInt(5, slmcId);
            preparedStatement.setInt(6, contactNumber);
            preparedStatement.setString(7, address);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Doctorbean> getAll()
    {
    
    	try {
    		 List<Doctorbean> doctors = new ArrayList<>();

    	    	String query = "SELECT * FROM `doctor`";
    		PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();	
            
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
           return doctors;
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }
}