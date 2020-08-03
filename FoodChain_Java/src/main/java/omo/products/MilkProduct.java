package omo.products;

public class MilkProduct extends Product{
    private String name = "Milk";

    private int price =  15 + (int) (Math.random() * 25);

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
