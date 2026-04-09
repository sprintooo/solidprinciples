package org.patidar.solid.srp.amazonOrderHard.solution;

import java.util.Map;

class OrderService {
    private final OrderValidator orderValidator;
    private final PricingService pricingService;
    private final PaymentProcessorFactory paymentProcessorFactory;
    private final OrderRepository orderRepository;
    private final NotificationService notificationService;

    public OrderService(OrderValidator orderValidator,
                        PricingService pricingService,
                        PaymentProcessorFactory paymentProcessorFactory,
                        OrderRepository orderRepository,
                        NotificationService notificationService) {
        this.orderValidator = orderValidator;
        this.pricingService = pricingService;
        this.paymentProcessorFactory = paymentProcessorFactory;
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
    }

    void placeOrder(Order order,
                    String paymentType,
                    DiscountStrategy discountStrategy) {

        // 1. validate
        orderValidator.validate(order);

        // 2. calculate price
        int totalPrice = pricingService.calculatePrice(order);

        // 3. apply discount
        int discountedPrice = discountStrategy.applyDiscount(totalPrice);

        // 4. process payment - using factory to get payment processor at runtime
        PaymentProcessor paymentProcessor = paymentProcessorFactory.getPaymentProcessor(paymentType);
        paymentProcessor.processPayment(discountedPrice);

        // 5. save order
        orderRepository.save(order);

        // 6. notify
        notificationService.send("Order placed successfully for: " + order.item);
    }
}

class Order {
    String item;
    int quantity;
    int price;
}

interface OrderValidator {
    void validate(Order order);
}
class OrderValidatorImpl implements OrderValidator {
    @Override
    public void validate(Order order) {
        System.out.println("Validating order: " + order.item);
    }
}

interface PricingService {
    int calculatePrice(Order order);
}

class SimplePricingService implements PricingService {
    @Override
    public int calculatePrice(Order order) {
        System.out.println("Calculating simple price: quantity * price");
        return order.quantity * order.price;
    }
}

class TaxIncludedPricingService implements PricingService {
    @Override
    public int calculatePrice(Order order) {
        System.out.println("Calculating price with 10% tax included");
        int basePrice = order.quantity * order.price;
        int tax = (int) (basePrice * 0.1); // 10% tax
        return basePrice + tax;
    }
}

class ShippingIncludedPricingService implements PricingService {
    @Override
    public int calculatePrice(Order order) {
        System.out.println("Calculating price with shipping cost included");
        int basePrice = order.quantity * order.price;
        int shippingCost = 50; // Flat shipping cost
        return basePrice + shippingCost;
    }
}

interface DiscountStrategy {
    int applyDiscount(int price);
}

class PercentageDiscountStrategy implements DiscountStrategy {
    @Override
    public int applyDiscount(int price) {
        System.out.println("Applying 10% discount");
        return (int) (price * 0.9);
    }
}

class FlatDiscountStrategy implements DiscountStrategy {
    @Override
    public int applyDiscount(int price) {
        System.out.println("Applying flat $20 discount");
        return price - 20;
    }
}

class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public int applyDiscount(int price) {
        System.out.println("No discount applied");
        return price;
    }
}


interface PaymentProcessor {
    void processPayment(int amount);
}

class CreditCardPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(int amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
}

class DebitCardPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(int amount) {
        System.out.println("Processing debit card payment of $" + amount);
    }
}

class PayPalPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(int amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}

class PaymentProcessorFactory{
    private final Map<String, PaymentProcessor> processorMap;

    PaymentProcessorFactory(Map<String, PaymentProcessor> processorMap) {
        this.processorMap = processorMap;
    }

    PaymentProcessor getPaymentProcessor(String paymentType) {
        PaymentProcessor processor = processorMap.get(paymentType);
        if (processor == null) {
            throw new IllegalArgumentException("Invalid payment type: " + paymentType);
        }
        return processor;
    }
}


interface NotificationService {
    void send(String message);
}

class EmailNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("Sending email notification: " + message);
    }
}

class SmsNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS notification: " + message);
    }
}

class PushNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("Sending push notification: " + message);
    }
}


interface OrderRepository {
    void save(Order order);
}

class DatabaseOrderRepository implements OrderRepository {
    @Override
    public void save(Order order) {
        System.out.println("Saving order to database: " + order.item);
    }
}

class FileOrderRepository implements OrderRepository {
    @Override
    public void save(Order order) {
        System.out.println("Saving order to file: " + order.item);
    }
}

class InMemoryOrderRepository implements OrderRepository {
    @Override
    public void save(Order order) {
        System.out.println("Saving order to in-memory storage: " + order.item);
    }
}
