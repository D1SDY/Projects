package omo.parties;

import omo.blockchain.Block;
import omo.blockchain.Chain;
import omo.Warehouse;
import omo.channels.Channel;
import omo.channels.Request;
import omo.operations.Production;
import omo.operations.Sale;
import omo.products.Product;

public class Processor extends Party{
    private String name;

    private Product product;
    private Warehouse warehouse;

    private final int PROCESS_PRICE = 20;

    private Request currentRequest;
    public Processor(String name){

        this.name=name;
        warehouse=new Warehouse(this);
    }


    @Override
    public void createRequest(Product product, int weight, Channel channel) {
        channel.addMessage("Processor " + name +" send request to channel for farmers");

        channel.addRequest(new Request(this, product, weight, channel));
    }

    @Override
    public void acceptRequest(Request request, Party sender) {
        currentRequest = request;

        request.getChannel().addMessage("\nProcessor need meet from farmer");
        createRequest(request.getProduct(),request.getAmount(), request.getChannel());
    }

    @Override
    public void createRespone(Product product, int weight, Channel channel) {
        Request request=new Request(this,product,weight,channel);

        int price = product.getPrice() + PROCESS_PRICE;

        product.setPrice(price);
        Sale sale = new Sale(currentRequest.getSender(), request.getSender(), request.getProduct(), request.getAmount(), price);
        sale.sale();

        request.getProduct().setPrice(price);
        warehouse.getProducts().remove(product);
        Chain.getInstance().addBlock(new Block(request,Chain.getInstance().getPrevBlockHash()));
        channel.addResponse(request);

    }

    @Override
    public void acceptResponse(Request request, Party sender) {
        currentRequest.getChannel().addMessage("\nPrijal obejdnavku od " + sender.getName());


        productProcess(request.getProduct(), request.getAmount());
        warehouse.addProduct(request.getProduct(), request.getAmount());

        createRespone(request.getProduct(), request.getAmount(), request.getChannel());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void productProcess(Product product, int amount) {

        Production production = new Production(product, this,amount);
        production.process();

    }

    public void addMessageToChannel(String message){
        currentRequest.getChannel().addMessage(message);
    }

    public void setWarehouse(Warehouse warehouse){
        this.warehouse = warehouse;
    }

    public Warehouse getWarehouse(){
        return warehouse;
    }
}
