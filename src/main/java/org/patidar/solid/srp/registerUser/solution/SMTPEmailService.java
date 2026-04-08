package org.patidar.solid.srp.registerUser.solution;

import org.springframework.stereotype.Service;

@Service
public class SMTPEmailService implements EmailService {
    @Override
    public void sendWelcomeEmail(String email) {
        System.out.println("EmailServiceImpl: Sending welcome email to " + email);
    }
}
