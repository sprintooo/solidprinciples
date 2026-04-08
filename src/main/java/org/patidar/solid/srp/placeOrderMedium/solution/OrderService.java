package org.patidar.solid.srp.placeOrderMedium.solution;

/**
 * Responsibility: Orchestrating the order placement workflow
 * Single Reason to Change: Order placement business logic/workflow changes
 * <p>
 * This class follows SRP by delegating specific responsibilities to dedicated classes.
 * It only coordinates the order placement process.
 */
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentProcessor paymentProcessor;
    private final EmailService emailService;
    private final PricingService pricingService;

    public OrderService(OrderRepository orderRepository, PaymentProcessor paymentProcessor,
                       EmailService emailService, PricingService pricingService) {
        this.orderRepository = orderRepository;
        this.paymentProcessor = paymentProcessor;
        this.emailService = emailService;
        this.pricingService = pricingService;
    }

    public void placeOrder(String item, int quantity, String email) {
        int price = pricingService.calculatePrice(item, quantity);
        orderRepository.saveOrder(item, quantity, price);
        paymentProcessor.processPayment(price);
        emailService.sendEmail(email, "Your order has been placed. Total: " + price);
    }
}

/**
 * Responsibility: Database operations for Order entities
 * Single Reason to Change: Database schema or persistence logic changes
 */
interface OrderRepository {
    void saveOrder(String item, int quantity, int price);
}

class MangoDBOrderRepository implements OrderRepository {
    @Override
    public void saveOrder(String item, int quantity, int price) {
        System.out.println("Saving order to MongoDB");
    }
}

/**
 * Responsibility: Payment processing
 * Single Reason to Change: Payment gateway or payment logic changes
 */
interface PaymentProcessor {
    void processPayment(int price);
}

class UPIPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(int price) {
        System.out.println("Processing payment with UPI");
    }
}

/**
 * Responsibility: Email communication
 * Single Reason to Change: Email service provider or email template changes
 */
interface EmailService {
    void sendEmail(String email, String message);
}

class SMTPEmailService implements EmailService {
    @Override
    public void sendEmail(String email, String message) {
        System.out.println("Sending email to " + email);
    }
}

/**
 * OPEN-CLOSED PRINCIPLE (OCP) Implementation
 *
 * Responsibility: Calculate pricing for orders
 *
 * This interface is OPEN for extension (you can add new pricing strategies)
 * but CLOSED for modification (existing implementations don't need to change)
 *
 * Benefits:
 * - Add new pricing strategies without modifying existing code
 * - Each pricing strategy is independent and testable
 * - Easy to switch between different pricing models
 */
interface PricingService {
    int calculatePrice(String item, int quantity);
}

/**
 * Standard pricing strategy: Fixed price per item
 */
class StandardPricingService implements PricingService {
    private static final int PRICE_PER_ITEM = 100;

    @Override
    public int calculatePrice(String item, int quantity) {
        int totalPrice = quantity * PRICE_PER_ITEM;
        System.out.println("Standard Pricing: " + quantity + " x " + PRICE_PER_ITEM + " = " + totalPrice);
        return totalPrice;
    }
}