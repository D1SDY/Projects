package omo.productState;

import omo.products.Product;

public class ProcessedState extends State {

    public ProcessedState(Product context){
        super(context);
    }

    @Override
    protected void changeToNextState() {
        context.setState(new ProcessedState(context));
    }
}
