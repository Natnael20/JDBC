import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/University?serverTimezone=Europe/Stockholm";
        String username = "root";
        String password = "";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Attempt to establish a connection to the database
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            if (connection != null) {
                System.out.println("Connection to MySQL database established successfully!");
                // Create a SQL statement
                Statement statement = connection.createStatement();

                // Execute the SQL query to retrieve all students
                ResultSet resultSet = statement.executeQuery("SELECT * FROM students");

                // Process the results
                while (resultSet.next()) {
                    int studentId = resultSet.getInt("student_id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String dateOfBirth = resultSet.getString("date_of_birth");
                    String email = resultSet.getString("email");
                    String major = resultSet.getString("major");
                    // Add more columns as needed

                    // Print student information
                    System.out.println("Student ID: " + studentId);
                    System.out.println("Name: " + firstName + " " + lastName);
                    System.out.println("Date of brith " + dateOfBirth);
                    System.out.println("Email " + email);
                    System.out.println("Major " + major);
                    // Print more columns as needed
                }

                // Close the result set, statement, and connection
                resultSet.close();
                statement.close();
                connection.close();
                connection.close(); // Close the connection when done
            } 
            else {
                System.out.println("Failed to connect to the database.");
            }
        } 
        catch (ClassNotFoundException e) {	
            System.err.println("JDBC driver not found: " + e.getMessage());   
        } 
        catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }

    }
}