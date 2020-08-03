package omo.productState;

import omo.products.Product;

public class SellableState extends State {

    public SellableState(Product context){
        super(context);
    }

    @Override
    protected void changeToNextState() {
        context.setState(new SoldState(context));
    }
}
