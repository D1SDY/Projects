package omo.parties;

import omo.Warehouse;
import omo.channels.Channel;
import omo.channels.Request;
import omo.products.Product;

public abstract class Party {

    private String name;

    private Warehouse warehouse;

    private Request currentRequest;
    public abstract void createRequest(Product product, int weight, Channel channel);
    public abstract void acceptRequest(Request request, Party sender);
    public abstract  void createRespone(Product product,int weight, Channel channel);
    public abstract  void acceptResponse(Request request,Party sender);

    public String getName() {
        return name;
    }

    public Request getCurrentRequest() {
        return currentRequest;
    }

    public abstract void addMessageToChannel(String message);
}
