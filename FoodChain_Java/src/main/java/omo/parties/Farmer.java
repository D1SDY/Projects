package omo.parties;

import omo.blockchain.Block;
import omo.blockchain.Chain;
import omo.Warehouse;
import omo.channels.Channel;
import omo.channels.Request;
import omo.operations.Growing;
import omo.operations.Sale;
import omo.products.Product;

public class Farmer extends Party {

    private String name;

    private Warehouse warehouse;

    private Request currentRequest;

    public Farmer(String name) {
        this.name = name;
        warehouse = new Warehouse(this);
    }

    @Override
    public void createRequest(Product product, int weight, Channel channel) {

        channel.addRequest(new Request(this, product, weight, channel));
    }


    @Override
    public void acceptRequest(Request request, Party sender) {

        currentRequest = request;

        request.getChannel().addMessage("\nFarmer " + this.name + " prijal pozadavek od" + request.getSender().getName());

        productGrow(request.getProduct(), request.getAmount());

        createRespone(request.getProduct(),request.getAmount(),request.getChannel());

    }

    @Override
    public void createRespone(Product product, int weight, Channel channel) {


        Request request=new Request(this,product,weight,channel);

        int price = request.getProduct().getPrice();
        Sale sale = new Sale(currentRequest.getSender(), request.getSender(), request.getProduct(), request.getAmount(), price);
        sale.sale();

        warehouse.getProducts().remove(product);
        Chain.getInstance().addBlock(new Block(request,Chain.getInstance().getPrevBlockHash()));
        channel.addResponse(request);

    }

    @Override
    public void acceptResponse(Request request, Party sender) {
    }

    public void productGrow(Product product, int amount) {

        Growing growing = new Growing(product, this, amount);

        growing.grow();

    }


    public void addMessageToChannel(String message){
        currentRequest.getChannel().addMessage(message);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
