package omo.operations;

import omo.parties.Party;
import omo.products.Product;

public class Sale {

    private Party buyer;
    private Party seller;
    private Product product;
    private int amount;
    private int price;

    public Sale(Party buyer, Party seller, Product product, int amount, int price) {
        this.buyer = buyer;
        this.seller = seller;
        this.product = product;
        this.amount = amount;
        this.price = price;
    }

    public void sale(){

        String info = "\nProdej: \nProdejce " + seller.getName() + ".\nZakaznik " + buyer.getName() + ".\n Product "
                + product.getName() + ".\n Weight " + amount + ".\n Price " + price + ".\n Total price " + price * amount;

        product.setOwner(buyer);

        seller.addMessageToChannel(info);
//        Transaction transaction = new Transaction(seller, info, null);
    }
}
