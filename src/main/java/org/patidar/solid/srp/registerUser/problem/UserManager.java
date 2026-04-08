package org.patidar.solid.srp.registerUser.problem;

class UserManager {

    void registerUser(String name, String email) {
        // 1. Save user to database
        System.out.println("Saving user to database");

        // 2. Send welcome email
        System.out.println("Sending welcome email");

        // 3. Log registration
        System.out.println("Logging user registration");
    }
}