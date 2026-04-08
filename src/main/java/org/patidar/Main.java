package org.patidar;

import org.patidar.solid.srp.registerUser.solution.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SRP Solution Demo ===\n");

        // Create the dependencies manually (without Spring DI for simplicity)
        UserRepository userRepository = new MySQLUserRepository();
        EmailService emailService = new SMTPEmailService();

        // Create the registration service with dependencies
        UserRegistrationService registrationService = new UserRegistrationService(userRepository, emailService);

        // Register a user
        registrationService.registerUser("John Doe", "john.doe@example.com");

        System.out.println("\n=== Registration Complete ===");
        System.out.println("\nBenefits of this approach:");
        System.out.println("1. Each class has a single responsibility");
        System.out.println("2. Changes to email logic don't affect database or logging code");
        System.out.println("3. Easy to test each component independently");
        System.out.println("4. Easy to replace implementations (e.g., switch email provider)");
    }
}
