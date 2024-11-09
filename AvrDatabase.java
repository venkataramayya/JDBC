import java.sql.*;

public class AvrDatabase {
    public static void main(String[] args) {

        final String URL = "jdbc:mysql://localhost:3306/my3"; // Corrected 'loacalhost' to 'localhost'
        final String password = "Avr@2003";
        final String username = "root";

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish a connection
            Connection con = DriverManager.getConnection(URL, username, password);
            
            // SQL query
            String query = "select * from avr";
            
            // Create a statement
            Statement statement = con.createStatement();
            
            // Execute query and get results
            ResultSet result = statement.executeQuery(query);

            // Iterate over the result set and print the data
            while (result.next()) {
                System.out.println("ID: " + result.getInt("id"));
                System.out.println("Name: " + result.getString("name"));
                System.out.println("Roll No: " + result.getInt("roll_no"));
                System.out.println("Address: " + result.getString("adress"));
                System.out.println("___________");
            }

            // Close resources
            result.close();
            statement.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
