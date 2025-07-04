import java.time.LocalDate;

import model.Cart;
import model.Customer;
import model.Product;
import service.Checkout;

public class App {
    public static void main(String[] args) {
        try {

            Product cheese = new Product("Cheese", 100, 10, 0.4, LocalDate.of(2025, 7, 10));
            Product tv = new Product("TV", 1500, 5, 8.5, null);
            Product scratchCard = new Product("Scratch Card", 25, 100, null, null);

            Customer youssef = new Customer("Youssef Yasser", 2000);

            Cart cart = new Cart();

            cart.add(cheese, 2);
            cart.add(tv, 1);
            cart.add(scratchCard, 3);

            Checkout checkout = new Checkout();

            checkout.process(youssef, cart);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
