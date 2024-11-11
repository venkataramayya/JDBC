import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Deletee {
    public static void main(String[] args) {
        final String URL = "jdbc:mysql://localhost:3306/my3";
        final String username = "root";
        final String password = "Avr@2003";

        try {
            // Step 1: Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish a connection
            Connection con = DriverManager.getConnection(URL, username, password);

            // Step 3: Create a DELETE SQL query
            String deleteSQL = "DELETE FROM avr WHERE id = ?"; // Adjust the column name as per your table

            // Step 4: Create a PreparedStatement
            PreparedStatement ps = con.prepareStatement(deleteSQL);

            // Step 5: Set the id of the record to delete (replace 1 with the actual id you want to delete)
            int idToDelete = 1;  // Replace with the actual ID you want to delete
            ps.setInt(1, idToDelete);

            // Step 6: Execute the update query
            int rowsAffected = ps.executeUpdate();

            // Step 7: Check the result
            if (rowsAffected > 0) {
                System.out.println("Record with ID " + idToDelete + " deleted successfully.");
            } else {
                System.out.println("No record found with ID " + idToDelete);
            }

            // Step 8: Close the resources
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
