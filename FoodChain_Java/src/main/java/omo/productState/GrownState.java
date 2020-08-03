package omo.productState;

import omo.products.Product;

public class GrownState extends State {

    public GrownState(Product context){
        super(context);
    }

    @Override
    protected void changeToNextState() {
        context.setState(new ProcessedState(context));
    }
}
