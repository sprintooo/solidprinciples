package org.patidar.solid.srp.placeOrderMedium.solution;

/**
 * Demo class to demonstrate the OrderService SRP implementation
 */
public class OrderServiceDemo {
    
    public static void runDemo() {
        System.out.println("--- Demo: Order Placement (SRP Solution) ---");
        
        // Create the dependencies for order service
        OrderRepository orderRepository = new MangoDBOrderRepository();
        PaymentProcessor paymentProcessor = new UPIPaymentProcessor();
        EmailService emailService = new SMTPEmailService();
        PricingService pricingService = new StandardPricingService();

        // Create the order service with dependencies
        OrderService orderService = new OrderService(orderRepository, paymentProcessor, emailService, pricingService);

        // Place an order
        orderService.placeOrder("Laptop", 2, "customer@example.com");

        System.out.println("\n=== Order Placement Complete ===");
    }
}
