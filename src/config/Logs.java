package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class Logs{

   
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void logFunctionCall(String functionName) {
        String sql = "INSERT INTO function_logs (function_name) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, functionName);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
}
