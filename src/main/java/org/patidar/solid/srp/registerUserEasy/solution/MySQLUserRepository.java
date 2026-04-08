package org.patidar.solid.srp.registerUserEasy.solution;

import org.springframework.stereotype.Repository;

@Repository
public class MySQLUserRepository implements UserRepository {
    @Override
    public void save(String name, String email) {
        System.out.println("UserRepository: Saving user to database - " + name + " (" + email + ")");
    }
}
