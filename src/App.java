import java.time.LocalDate;

import model.Cart;
import model.Customer;
import model.Product;
import service.Checkout;

public class App {
    public static void main(String[] args) {
        testNormalCheckout();
        testExpiredProduct();
        testInsufficientBalance();
        testExceedQuantity();
        testEmptyCart();
    }

    static void testNormalCheckout() {
        System.out.println("Test: Normal Checkout ");
        try {
            Product cheese = new Product("Cheese", 100, 10, 0.4, LocalDate.of(2025, 7, 10));
            Product tv = new Product("TV", 1500, 5, 8.5, null);
            Product scratchCard = new Product("Scratch Card", 25, 100, null, null);

            Customer customer = new Customer("Youssef", 2000);
            Cart cart = new Cart();

            cart.add(cheese, 2);
            cart.add(tv, 1);
            cart.add(scratchCard, 3);

            new Checkout().process(customer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void testExpiredProduct() {
        System.out.println();
        System.out.println("******************************************");
        System.out.println();
        
        System.out.println("Expired Product");
        try {
            Product expiredCheese = new Product("Cheese", 80, 5, 0.5, LocalDate.of(2020, 1, 1));
            Customer customer = new Customer("youssefYasser1", 500);
            Cart cart = new Cart();
            cart.add(expiredCheese, 1); // should fail
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void testInsufficientBalance() {
        System.out.println();
        System.out.println("******************************************");
        System.out.println();
        System.out.println("Test: Insufficient Balance");
        try {
            Product tv = new Product("TV", 1500, 1, 9.0, null);
            Customer poorGuy = new Customer("youssefYasser2", 200); // too little balance
            Cart cart = new Cart();
            cart.add(tv, 1);

            new Checkout().process(poorGuy, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void testExceedQuantity() {
        System.out.println();
        System.out.println("******************************************");
        System.out.println();
        System.out.println("Test: Quantity Exceeds Stock");
        try {
            Product biscuits = new Product("Biscuits", 20, 5, 0.2, LocalDate.of(2025, 9, 1));
            Cart cart = new Cart();
            cart.add(biscuits, 6);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void testEmptyCart() {
        System.out.println();
        System.out.println("******************************************");
        System.out.println();
        System.out.println("Test: Empty Cart");
        try {
            Customer customer = new Customer("youssefYasser3", 1000);
            Cart emptyCart = new Cart();

            new Checkout().process(customer, emptyCart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
