package omo.parties;

import omo.blockchain.Block;
import omo.blockchain.Chain;
import omo.Warehouse;
import omo.channels.Channel;
import omo.channels.Request;
import omo.operations.Sale;
import omo.productState.SellableState;
import omo.products.Product;

public class Retailer extends Party {

    private String name;
    private Warehouse warehouse;

    private boolean iHave;
    private Request currentRequest;
    private final int CHEAT_PRICE = 10;


    @Override
    public void createRequest(Product product, int weight, Channel channel) {

        channel.addMessage("\nRetailer " + name + " send request to channel for product " + product.getName());

        channel.addRequest(new Request(this, product, weight, channel));
    }

    @Override
    public void acceptRequest(Request request, Party sender) {
        int avaibleAmount=0;
        currentRequest = request;
        for (Product product : warehouse.getProducts().keySet()) {
            if (product.getName().equals(request.getProduct().getName()))
                avaibleAmount=warehouse.getProducts().get(product);
        }


        if(avaibleAmount>=request.getAmount()){
            iHave = true;
            currentRequest.getChannel().addMessage("\nRetailer " + name + " has " + request.getProduct().getName());
            createRespone(request.getProduct(), request.getAmount(), request.getChannel());
        }
        else{
            iHave = false;
            createRequest(request.getProduct(),request.getAmount()-avaibleAmount, request.getChannel());
        }
    }


    @Override
    public void createRespone(Product product, int weight, Channel channel) {
        Request request=new Request(this,product,weight,channel);

        int price = product.getPrice() + CHEAT_PRICE;

        Sale sale = new Sale(currentRequest.getSender(), request.getSender(), request.getProduct(), currentRequest.getAmount(), price);
        sale.sale();

        warehouse.getProducts().remove(product);
        Chain.getInstance().addBlock(new Block(request,Chain.getInstance().getPrevBlockHash()));
        channel.addResponse(request);
    }

    @Override
    public void acceptResponse(Request request, Party sender) {
        currentRequest.getChannel().addMessage("\nRetailer " + name + " prijal obejdnavku od " + sender.getName());

        request.getProduct().setState(new SellableState(request.getProduct()));
//        System.out.println("Retailer " + this.getName() + " set state Sellable for product" + request.getProduct().getName());
        warehouse.addProduct(request.getProduct(), request.getAmount());

        createRespone(request.getProduct(), request.getAmount(), request.getChannel());
    }

    public Retailer(String name) {
        this.name = name;
        warehouse = new Warehouse(this);
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

    public boolean isiHave() {
        return iHave;
    }

    public void setiHave(boolean iHave) {
        this.iHave = iHave;
    }
}
