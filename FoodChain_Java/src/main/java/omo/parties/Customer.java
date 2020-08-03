package omo.parties;

import omo.channels.Channel;
import omo.channels.Request;
import omo.productState.SoldState;
import omo.products.Product;

public class Customer extends Party {
    private String name;
    private int money;

    public Customer(String name){
        this.name=name;
    }

    @Override
    public void createRequest(Product product, int weight, Channel channel) {
        Request request = new Request((Party)this, product, weight, channel);

        channel.addMessage("\n\n\nRequest");
        channel.addMessage("\nCustomer " + name + " wants " + product.getName() + " weight " + weight );

        channel.addRequest(request);
    }

    @Override
    public void acceptRequest(Request request, Party sender) {

    }

    @Override
    public void createRespone(Product product, int weight, Channel channel) {

    }

    @Override
    public void acceptResponse(Request request, Party sender) {

        request.getChannel().addMessage("\nCustomer " +name +" prijal obejdnavku od " + sender.getName());

        request.getProduct().setState(new SoldState(request.getProduct()));
    }

    public void addMessageToChannel(String message){
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
