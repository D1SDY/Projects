package omo.operations;

import omo.parties.Processor;
import omo.productState.ProcessedState;
import omo.products.Product;

public class Production {
    private Product product;
    private Processor processor;
    private int amount;

    public Production(Product product, Processor processor, int amount) {
        this.product = product;
        this.processor = processor;
        this.amount=amount;
        product.setState(new ProcessedState(product));
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public void process(){

        processor.addMessageToChannel("Processor " + processor.getName() + " zacal vyrobeni " + product.getName());

        product.setState(new ProcessedState(product));

        processor.addMessageToChannel("Processor " + processor.getName() + " ukoncil vyrobeni " + product.getName() + " a vyrobil " + amount);

        processor.getWarehouse().addProduct(product, amount);

        processor.addMessageToChannel("Processor " + processor.getName() + "poslal do sveho skladu");

    }
}
