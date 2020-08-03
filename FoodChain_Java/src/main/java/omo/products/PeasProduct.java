package omo.products;

public class PeasProduct extends Product{
    private String name = "Peas";

    private int price =  10 + (int) (Math.random() * 15);

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
