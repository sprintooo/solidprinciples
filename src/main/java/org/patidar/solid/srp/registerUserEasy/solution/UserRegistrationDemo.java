package org.patidar.solid.srp.registerUserEasy.solution;

/**
 * Demo class to demonstrate the UserRegistrationService SRP implementation
 */
public class UserRegistrationDemo {
    
    public static void runDemo() {
        System.out.println("--- Demo: User Registration (SRP Solution) ---");
        
        // Create the dependencies manually (without Spring DI for simplicity)
        UserRepository userRepository = new MySQLUserRepository();
        EmailService emailService = new SMTPEmailService();

        // Create the registration service with dependencies
        UserRegistrationService registrationService = new UserRegistrationService(userRepository, emailService);

        // Register a user
        registrationService.registerUser("John Doe", "john.doe@example.com");

        System.out.println("\n=== Registration Complete ===");
    }
}
