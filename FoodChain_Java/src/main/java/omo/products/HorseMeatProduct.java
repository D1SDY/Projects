package omo.products;

public class HorseMeatProduct extends Product{
    private String name = "Horse Meat";

    private int price =  200 + (int) (Math.random() * 300);

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
