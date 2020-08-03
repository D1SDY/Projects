package omo.parties;

import omo.Warehouse;
import omo.channels.Channel;
import omo.channels.Request;
import omo.operations.Sale;
import omo.productState.SellableState;
import omo.products.Product;

public class Supplier extends Party {

    private String name;
    private Warehouse warehouse;

    private final int SUPPLYPRICE = 15;

    private Request currentRequest;


    @Override
    public void createRequest(Product product, int weight, Channel channel) {

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
            System.out.println("\nSupplier " + name + " has " + request.getProduct().getName());
            createRespone(request.getProduct(), request.getAmount(), request.getChannel());
        }
        else{
            createRequest(request.getProduct(),request.getAmount(), request.getChannel());
        }
    }

    @Override
    public void createRespone(Product product, int weight, Channel channel) {
        Request request=new Request(this,product,weight,channel);

        int price = product.getPrice() + SUPPLYPRICE;

        Sale sale = new Sale(currentRequest.getSender(), request.getSender(), request.getProduct(), currentRequest.getAmount(), price);
        sale.sale();

        warehouse.getProducts().remove(product);

        channel.addResponse(request);
    }

    @Override
    public void acceptResponse(Request request, Party sender) {
        System.out.println("\nPrijal obejdnavku od " + sender.getName());


        request.getProduct().setState(new SellableState(request.getProduct()));
        System.out.println("Supplier " + this.getName() + " set state Sellable for product" + request.getProduct().getName());
        warehouse.addProduct(request.getProduct(), request.getAmount());

        createRespone(request.getProduct(), request.getAmount(), request.getChannel());
    }

    public Supplier(String name) {
        this.name = name;
        warehouse = new Warehouse(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public void addMessageToChannel(String message) {

    }

    public void setName(String name) {
        this.name = name;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
        warehouse.setOwner(this);
    }

}
