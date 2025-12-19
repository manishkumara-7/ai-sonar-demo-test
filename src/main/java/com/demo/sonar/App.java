package com.demo.sonar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import java.util.logging.Level;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws Exception {

        String userInput = args.length > 0 ? args[0] : "1";

        // ✅ Fixed: Use PreparedStatement to prevent SQL injection
        String query = "SELECT * FROM users WHERE id = ?";

        // ✅ Fixed: Use environment variables instead of hardcoded credentials
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");

        if (dbUrl == null || dbUser == null || dbPassword == null) {
            logger.log(Level.SEVERE, "Database credentials not configured. Please set DB_URL, DB_USER, and DB_PASSWORD environment variables.");
            throw new IllegalStateException("Database credentials must be provided via environment variables");
        }

        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

        // ✅ Fixed: Use PreparedStatement with parameterized query
        PreparedStatement stmt = conn.prepareStatement(query);
        try {
            // Parse and validate user input as integer to prevent injection
            int userId = Integer.parseInt(userInput);
            stmt.setInt(1, userId);
            
            ResultSet rs = stmt.executeQuery();
            // Process results...
            rs.close();
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Invalid user input: " + userInput, e);
            throw new IllegalArgumentException("User ID must be a valid integer", e);
        } finally {
            stmt.close();
            conn.close();
        }

        // ✅ Fixed: Null check before calling method
        String data = null;
        if (data != null) {
            logger.info(data.toLowerCase());
        } else {
            logger.warning("Data is null, cannot process");
        }
    }
}
