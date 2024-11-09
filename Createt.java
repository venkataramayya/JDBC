import java.sql.*;

public class Createt {
    public static void main(String[] args) {
        final String URL = "jdbc:mysql://localhost:3306/my3";
        final String username = "root";
        final String password = "Avr@2003";

        try {
            // Establish connection
            Connection con = DriverManager.getConnection(URL, username, password);
            
            // SQL insert query
            String query = "INSERT INTO avr (id,name, roll_no, adress) VALUES (?,?, ?, ?)";
            
            // Create PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(query);
            
            // Set the values
            pstmt.setInt(1, 101);

            pstmt.setString(2, "John Doe");
            pstmt.setInt(3, 101);
            pstmt.setString(4, "Hyderabad");

            // Execute the insert operation
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " record(s) inserted.");

            // Close resources
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
