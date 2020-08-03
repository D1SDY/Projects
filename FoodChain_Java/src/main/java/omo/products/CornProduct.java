package omo.products;

public class CornProduct extends Product {

    private String name = "Corn";

    private int price =  10 + (int) (Math.random() * 20);

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
    @Override
    public String getName() {
        return name;
    }

}
