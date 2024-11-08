import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLConnectionExample {
    public static void main(String[] args) {
        // Database URL, username, and password
        String url = "jdbc:mysql://localhost:3306/my3tech";
        String username = "root";   // Your MySQL username
        String password = "Avr@2003";   // Your MySQL password

        try {
            // Load the MySQL JDBC driver (optional for JDBC 4.0+)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Execute a query
            String query = "SELECT customer_id, firstname, email FROM customer";  // Make sure the column names match
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result set
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("customer_id"));
                System.out.println("Name: " + resultSet.getString("firstname"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("---------");
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

