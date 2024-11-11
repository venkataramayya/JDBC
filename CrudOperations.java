import java.sql.*;

public class CrudOperations {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/my3";  // replace with your DB info
        String username = "root";
        String password = "Avr@2003";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Step 1: Create records in table1 and table2
            createRecords(connection);

            // Step 2: Read common records
            readCommonRecords(connection);

            // Step 3: Read different records
            readDifferentRecords(connection);

            // Step 4: Update a record in table1
            updateRecord(connection);

            // Step 5: Delete a record from table1
            deleteRecord(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createRecords(Connection connection) throws SQLException {
        String insertTable1 = "INSERT INTO table1 (id, name) VALUES (?, ?)";
        String insertTable2 = "INSERT INTO table2 (id, name) VALUES (?, ?)";

        try (PreparedStatement ps1 = connection.prepareStatement(insertTable1);
             PreparedStatement ps2 = connection.prepareStatement(insertTable2)) {

            // Insert data into table1
            ps1.setInt(1, 1);  // id
            ps1.setString(2, "Alice");  // name
            ps1.executeUpdate();

            ps1.setInt(1, 2);
            ps1.setString(2, "Bob");
            ps1.executeUpdate();

            // Insert data into table2
            ps2.setInt(1, 1);  // id
            ps2.setString(2, "Alice");  // common record
            ps2.executeUpdate();

            ps2.setInt(1, 3);
            ps2.setString(2, "Charlie");
            ps2.executeUpdate();

            System.out.println("Records inserted successfully.");
        }
    }

    private static void readCommonRecords(Connection connection) throws SQLException {
        String commonRecordsQuery = "SELECT id, name FROM table1 INTERSECT SELECT id, name FROM table2";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(commonRecordsQuery)) {

            System.out.println("Common Records:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        }
    }

    private static void readDifferentRecords(Connection connection) throws SQLException {
        String differentRecordsQuery = "SELECT id, name FROM table1 UNION SELECT id, name FROM table2 "
                + "EXCEPT SELECT id, name FROM table1 INTERSECT SELECT id, name FROM table2";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(differentRecordsQuery)) {

            System.out.println("Different Records:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        }
    }

    private static void updateRecord(Connection connection) throws SQLException {
        String updateTable1 = "UPDATE table1 SET name = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(updateTable1)) {
            ps.setString(1, "Updated Alice");  // new name
            ps.setInt(2, 1);  // record to update
            ps.executeUpdate();

            System.out.println("Record updated in table1.");
        }
    }

    private static void deleteRecord(Connection connection) throws SQLException {
        String deleteFromTable1 = "DELETE FROM table1 WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(deleteFromTable1)) {
            ps.setInt(1, 2);  // delete Bob
            ps.executeUpdate();

            System.out.println("Record deleted from table1.");
        }
    }
}
