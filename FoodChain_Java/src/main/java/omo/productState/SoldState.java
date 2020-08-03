package omo.productState;

import omo.products.Product;

public class SoldState extends State {

    public SoldState(Product context) {
        super(context);
    }

    protected void changeToNextState() {

        context.setState(new SoldState(context));
    }

}
