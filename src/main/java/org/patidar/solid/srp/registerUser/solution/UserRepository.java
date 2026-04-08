package org.patidar.solid.srp.registerUser.solution;

/**
 * Responsibility: Database operations for User entities
 * Single Reason to Change: Database schema or persistence logic changes
 */
public interface UserRepository {
    void save(String name, String email);
}
