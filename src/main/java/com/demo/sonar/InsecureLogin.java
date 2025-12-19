package com.demo.sonar;

public class InsecureLogin {

    // ❌ Hardcoded credentials (Sonar will flag this)
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";

    public static boolean login(String user, String pass) {
        if (user.equals(USERNAME) && pass.equals(PASSWORD)) {
            System.out.println("Login successful");
            return true;
        } else {
            System.out.println("Login failed");
            return false;
        }
    }

    public static void main(String[] args) {
        login("admin", "admin123");
    }
}
