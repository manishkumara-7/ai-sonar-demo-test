package com.demo.sonar;

public class InsecureLogin {

    /**
     * Performs a very simple login check without hardcoded credentials.
     * <p>
     * In a real application this method should delegate to a dedicated
     * authentication component that validates the supplied credentials
     * against a secure user store (e.g. database, LDAP, identity provider).
     *
     * @param user the username supplied by the caller
     * @param pass the password supplied by the caller
     * @return {@code true} if the basic validation passes, {@code false} otherwise
     */
    public static boolean login(String user, String pass) {
        if (user == null || pass == null || user.isBlank() || pass.isBlank()) {
            System.out.println("Login failed: missing credentials");
            return false;
        }

        // At this point we only demonstrate that non-empty credentials were provided.
        // Real authentication must be implemented elsewhere using secure practices.
        System.out.println("Login attempt for user: " + user);
        return true;
    }

    public static void main(String[] args) {
        String username = System.getenv("APP_USERNAME");
        String password = System.getenv("APP_PASSWORD");

        if (!login(username, password)) {
            System.exit(1);
        }
    }
}
