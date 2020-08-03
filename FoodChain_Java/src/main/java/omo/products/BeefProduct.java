package omo.products;

public class BeefProduct extends Product {

    private String name = "Beef";

    private int price =  100 + (int) (Math.random() * 150);

    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

}
