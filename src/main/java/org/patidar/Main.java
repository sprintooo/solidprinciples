package org.patidar;

import org.patidar.solid.srp.placeOrderMedium.solution.OrderServiceDemo;
import org.patidar.solid.srp.registerUserEasy.solution.UserRegistrationDemo;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SOLID Principles Demo ===\n");

        // Demo 1: User Registration (SRP)
        System.out.println("=== SRP Demo: User Registration ===\n");
        UserRegistrationDemo.runDemo();

        System.out.println("\n");

        // Demo 2: Order Placement (SRP)
        System.out.println("=== SRP Demo: Order Placement ===\n");
        OrderServiceDemo.runDemo();

        System.out.println("\n");

        // Demo 3: Amazon Order Placement (SRP - Advanced)
        System.out.println("=== SRP Demo: Amazon Order Placement (Advanced) ===\n");
        org.patidar.solid.srp.amazonOrderHard.solution.OrderServiceDemo.runDemo();

        System.out.println("\nSRP Benefits:");
        System.out.println("1. Each class has a single responsibility");
        System.out.println("2. Changes to email logic don't affect database or payment code");
        System.out.println("3. Easy to test each component independently");
        System.out.println("4. Easy to replace implementations (e.g., switch from UPI to Credit Card)");

        System.out.println("\n" + "=".repeat(70) + "\n");
    }
}
