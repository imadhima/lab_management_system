

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/TestTypeServlet")
public class test_type extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String DB_NAME = "user_db"; // Change this to your actual database name

    public void init() {
        Connection connection = (Connection) getServletContext().getAttribute("dbConnection");
        new test_typeDAO(connection);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String testName = request.getParameter("testName");
        String description = request.getParameter("description");

        boolean result = false;
		try {
			result = test_typeDAO.registerUser(testName, description);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (result) {
            response.sendRedirect("success.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
