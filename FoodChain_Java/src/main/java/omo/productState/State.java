package omo.productState;

import omo.products.Product;

public abstract class State {

    protected Product context;

    public State(Product context) {
        this.context = context;
    }

    protected abstract void changeToNextState();


    public Product getProduct() {
        return context;
    }

    public void setProduct(Product context) {
        this.context = context;
    }
}
