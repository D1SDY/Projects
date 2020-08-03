package omo.operations;

import omo.parties.Farmer;
import omo.parties.Party;
import omo.productState.GrownState;
import omo.products.Product;

public class Growing {

    private Product product;
    private Farmer farmer;
    private int amount;

    public Growing(Product product, Farmer farmer, int amount) {

        this.product = product;
        this.farmer = farmer;
        this.amount = amount;
    }

    public void grow(){
        farmer.addMessageToChannel("Farmer " + farmer.getName() + " zacal pestovani " + product.getName());
        product.setOwner(farmer);
        product.setState(new GrownState(product));

        farmer.addMessageToChannel("Farmer " + farmer.getName() + " ukoncil pestovani " + product.getName() + " a vypestoval " + amount);


        farmer.getWarehouse().addProduct(product, amount);

        farmer.addMessageToChannel("Farmer " + farmer.getName() + " poslal skladu produkt " + product.getName());

    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Party getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}
