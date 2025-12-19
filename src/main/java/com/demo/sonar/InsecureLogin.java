package com.demo.sonar;

import java.util.logging.Logger;
import java.util.logging.Level;

public class InsecureLogin {

    private static final Logger logger = Logger.getLogger(InsecureLogin.class.getName());

    /**
     * Performs authentication using credentials from environment variables.
     * <p>
     * In a real application this method should delegate to a dedicated
     * authentication component that validates the supplied credentials
     * against a secure user store (e.g. database, LDAP, identity provider).
     *
     * @param user the username supplied by the caller
     * @param pass the password supplied by the caller
     * @return {@code true} if the credentials are valid, {@code false} otherwise
     */
    public static boolean login(String user, String pass) {
        // ✅ Fixed: Removed hardcoded credentials - use environment variables or secure authentication service
        // ✅ Fixed: Use secure string comparison to prevent timing attacks
        String expectedUsername = System.getenv("APP_USERNAME");
        String expectedPassword = System.getenv("APP_PASSWORD");

        if (expectedUsername == null || expectedPassword == null) {
            logger.log(Level.SEVERE, "Authentication credentials not configured");
            return false;
        }

        if (user == null || pass == null) {
            logger.warning("Login failed: null credentials provided");
            return false;
        }

        // ✅ Fixed: Use secure comparison (constant-time comparison)
        boolean usernameMatch = secureEquals(user, expectedUsername);
        boolean passwordMatch = secureEquals(pass, expectedPassword);

        if (usernameMatch && passwordMatch) {
            // ✅ Fixed: Use logger instead of System.out for proper logging
            logger.info("Login successful for user: " + user);
            return true;
        } else {
            // ✅ Fixed: Use logger instead of System.out for proper logging
            logger.warning("Login failed: invalid credentials");
            return false;
        }
    }

    /**
     * Performs constant-time string comparison to prevent timing attacks.
     * 
     * @param a first string
     * @param b second string
     * @return true if strings are equal, false otherwise
     */
    private static boolean secureEquals(String a, String b) {
        if (a == null || b == null) {
            return a == b;
        }
        if (a.length() != b.length()) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < a.length(); i++) {
            result |= a.charAt(i) ^ b.charAt(i);
        }
        return result == 0;
    }

    public static void main(String[] args) {
        String username = System.getenv("APP_USERNAME");
        String password = System.getenv("APP_PASSWORD");

        if (username == null || password == null) {
            logger.log(Level.SEVERE, "APP_USERNAME and APP_PASSWORD environment variables must be set");
            System.exit(1);
        }

        if (!login(username, password)) {
            System.exit(1);
        }
    }
}
