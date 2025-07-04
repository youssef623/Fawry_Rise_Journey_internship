package service;

import model.Cart;
import model.CartItem;
import model.Customer;
import model.Product;
import model.Shippable;

import java.util.List;

public class Checkout {
    private final ShippingService shippingService = new ShippingService();
    private final double fee = 30.0;

    public void process(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        if (cart.hasExpiredItems()) {
            throw new IllegalStateException("Cart contains expired items");
        }

        double subtotal = cart.getSubtotal();
        double shippingFee = cart.getShippableItems().isEmpty() ? 0 : fee;
        double total = subtotal + shippingFee;

        customer.deduct(total);
        List<Shippable> toShip = cart.getShippableItems();
        shippingService.ship(toShip);

        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            Double itemTotal = item.getSubtotal();
            System.out.println(quantity + "x " + product.getName() + " " + String.format("%.2f", itemTotal));
        }
        System.out.println("----------------------------");
        System.out.println("Subtotal:   " + subtotal);
        System.out.println("Shipping:   " + shippingFee);
        System.out.println("Amount:     " + total);
    }
}
