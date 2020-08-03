package omo.products;

import omo.Warehouse;
import omo.parties.Party;
import omo.productState.State;

public abstract class Product {

    private String name;
    private Party owner;
    private State state;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Party getOwner() {
        return owner;
    }

    public void setOwner(Party owner) {
        this.owner = owner;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
