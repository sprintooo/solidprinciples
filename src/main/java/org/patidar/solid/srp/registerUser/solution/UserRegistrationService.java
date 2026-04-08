package org.patidar.solid.srp.registerUser.solution;

/**
 * Responsibility: Orchestrating the user registration workflow
 * Single Reason to Change: Registration business logic/workflow changes
 * <p>
 * This class follows SRP by delegating specific responsibilities to dedicated classes.
 * It only coordinates the registration process.
 */
public class UserRegistrationService {
    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserRegistrationService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public void registerUser(String name, String email) {
        userRepository.save(name, email);
        emailService.sendWelcomeEmail(email);
    }
}
