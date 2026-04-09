package org.patidar.solid.srp.amazonOrderHard.solution;

import org.springframework.lang.NonNull;

import java.util.Map;

/**
 * Demo class to demonstrate the OrderService SRP implementation
 */
public class OrderServiceDemo {

    private static final Map<String, PaymentProcessor> map = Map.of(
            "CreditCard", new CreditCardPaymentProcessor(),
            "DebitCard", new DebitCardPaymentProcessor(),
            "PayPal", new PayPalPaymentProcessor()
    );
    
    public static void runDemo() {
        System.out.println("--- Demo: Amazon Order Placement (SRP Solution) ---\n");
        
        // Create the dependencies for order service
        OrderService orderService = getOrderService();

        // Create an order
        Order order = new Order();
        order.item = "Laptop";
        order.quantity = 2;
        order.price = 1000;
        
        // Create discount strategy
        DiscountStrategy discountStrategy = new PercentageDiscountStrategy();
        
        // Place the order
        System.out.println("Placing order...\n");
        orderService.placeOrder(order, "CreditCard", discountStrategy);
        
        System.out.println("\n=== Order Placement Complete ===\n");
        
        
        // Demo with different implementations
        System.out.println("\n--- Demo 2: Different Payment Method & Discount ---\n");
        OrderService orderService2 = new OrderService(
            new OrderValidatorImpl(),
            new SimplePricingService(),
            new PaymentProcessorFactory(map),
            new InMemoryOrderRepository(),
            new SmsNotificationService()
        );

        Order order2 = new Order();
        order2.item = "Mouse";
        order2.quantity = 5;
        order2.price = 20;

        DiscountStrategy discountStrategy2 = new FlatDiscountStrategy();

        System.out.println("Placing second order...\n");
        orderService2.placeOrder(order2, "PayPal", discountStrategy2);

        System.out.println("\n=== Second Order Placement Complete ===");


        // Demo 3: Using Debit Card payment
        System.out.println("\n--- Demo 3: Debit Card Payment ---\n");

        Order order3 = new Order();
        order3.item = "Keyboard";
        order3.quantity = 1;
        order3.price = 50;

        DiscountStrategy discountStrategy3 = new NoDiscountStrategy();

        System.out.println("Placing third order with Debit Card...\n");
        orderService2.placeOrder(order3, "DebitCard", discountStrategy3);

        System.out.println("\n=== Third Order Placement Complete ===");
    }

    @NonNull
    private static OrderService getOrderService() {
        OrderValidator orderValidator = new OrderValidatorImpl();
        PricingService pricingService = new TaxIncludedPricingService();
        PaymentProcessorFactory paymentProcessorFactory = new PaymentProcessorFactory(map);
        OrderRepository orderRepository = new DatabaseOrderRepository();
        NotificationService notificationService = new EmailNotificationService();

        // Create the order service with dependencies
        OrderService orderService = new OrderService(
            orderValidator,
            pricingService,
            paymentProcessorFactory,
            orderRepository,
            notificationService
        );
        return orderService;
    }

    public static void main(String[] args) {
        runDemo();
    }
}
