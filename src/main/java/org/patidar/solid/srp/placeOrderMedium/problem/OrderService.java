package org.patidar.solid.srp.placeOrderMedium.problem;

class OrderService {

    void placeOrder(String item, int quantity, String email) {

        int price = quantity * 100;
        System.out.println("Total price: " + price);

        System.out.println("Saving order to DB");

        System.out.println("Processing payment");

        System.out.println("Sending email to " + email);
    }
}