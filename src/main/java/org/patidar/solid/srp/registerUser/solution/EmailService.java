package org.patidar.solid.srp.registerUser.solution;

/**
 * Responsibility: Email communication
 * Single Reason to Change: Email service provider or email template changes
 */
public interface EmailService {
    void sendWelcomeEmail(String email);
}
