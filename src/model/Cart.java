package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public List<Shippable> getShippableItems() {
    List<Shippable> shippables = new ArrayList<>();
    for (CartItem item : items) {
        Product p = item.getProduct();
        if (p.requiresShipping()) {
            for (int i = 0; i < item.getQuantity(); i++) {
                shippables.add(p); 
            }
        }
    }
    return shippables;
}


    public boolean hasExpiredItems() {
        for (CartItem item : items) {
            Product product = item.getProduct();
            if (product instanceof Expirable) {
                Expirable expirable = (Expirable) product;
                if (expirable.isExpired()) {
                    return true;
                }
            }
        }
        return false;
    }

}
