package omo;

import omo.parties.Party;
import omo.parties.Retailer;
import omo.products.Product;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    private Party owner;
    private Map<Product, Integer> products;

    public Warehouse(Party owner) {
        this.owner = owner;
        products = new HashMap<>();
    }

    public Party getOwner() {
        return owner;
    }

    public void setOwner(Party owner) {
        this.owner = owner;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public void addProduct(Product product, Integer amount){
        products.put(product, amount);
    }


}
