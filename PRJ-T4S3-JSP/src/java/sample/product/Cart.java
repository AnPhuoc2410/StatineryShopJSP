/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ANPHUOC
 */
public class Cart {

    private Map<String, Product> cart;

    public Cart() {
    }

    public Cart(Map<String, Product> cart) {
        this.cart = cart;
    }

    public Map<String, Product> getCart() {
        return cart;
    }

    public void setCart(Map<String, Product> cart) {
        this.cart = cart;
    }

    public boolean add(Product product) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }
            if (this.cart.containsKey(product.getProductID())) {
                int currentQuantity = this.cart.get(product.getProductID()).getQuantity();
                product.setQuantity(currentQuantity + product.getQuantity());
            }
            this.cart.put(product.getProductID(), product);
            check = true;
        } catch (Exception e) {
        }
        return check;

    }

    public boolean remove(String id) {
        boolean check = false;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(id)) {
                    this.cart.remove(id);
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }

    public boolean edit(String id, int quantity) {
        boolean check = false;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(id)) {
                    this.cart.get(id).setQuantity(quantity);
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }

    public Product search(String id) {
        Product product = null;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(id)) {
                    product = this.cart.get(id);
                }
            }
        } catch (Exception e) {
        }
        return product;
    }

    public int numberOfProduct() {
        int count = 0;
        if (this.cart != null) {
            for (Product products : this.cart.values()) {
                count += products.getQuantity();
            }
        }
        return count;
    }

}
