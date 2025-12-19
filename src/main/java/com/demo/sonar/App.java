package com.demo.sonar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class App {

    public static void main(String[] args) throws Exception {

        String userInput = args.length > 0 ? args[0] : "1=1";

        // ❌ SQL Injection vulnerability
        String query = "SELECT * FROM users WHERE id = " + userInput;

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb",
                "admin",
                "password123" // ❌ Hardcoded credential
        );

        Statement stmt = conn.createStatement();
        stmt.executeQuery(query);

        // ❌ Null Pointer vulnerability
        String data = null;
        System.out.println(data.toLowerCase());
    }
}
