package omo.channels;

import omo.parties.Party;
import omo.products.Product;

public class Request {
    private Party sender;
    private Product product;
    private int amount;
    private Channel channel;

    public Request(Party sender, Product product, int amount, Channel channel) {
        this.sender = sender;
        this.product = product;
        this.amount = amount;
        this.channel = channel;
    }

    public Party getSender() {
        return sender;
    }

    public void setSender(Party sender) {
        this.sender = sender;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

}
