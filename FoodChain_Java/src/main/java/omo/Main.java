package omo;

import omo.blockchain.Chain;
import omo.channels.*;
import omo.parties.*;
import omo.products.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Chain.getInstance().createGenesisBlock();
        Channel meatChannel = new MeatChannel();
        Channel otherChannel = new OtherChannel();

        Customer customer = new Customer("Customer Anton");

        Retailer retailer1 = new Retailer("Retailer Artur");
        retailer1.getWarehouse().addProduct(new BeefProduct(), 42);

//        Supplier supplier=new Supplier("supplier");
//        supplier.getWarehouse().addProduct(new BeefProduct(), 45);

        Processor processor = new Processor("Processor Agro");

        Farmer farmer = new Farmer("Farmer Makha");

        List<Party> participants = new ArrayList<>();
        participants.add(retailer1);
//        participants.add(supplier);

        participants.add(farmer);
        participants.add(customer);
        participants.add(processor);

        meatChannel.setParticipants(participants);
        otherChannel.setParticipants(participants);


//        customer.createRequest(new BeefProduct(), 46, channel);
        customer.createRequest(new BeefProduct(), 148, meatChannel);
        customer.createRequest(new BeefProduct(), 44, meatChannel);
        customer.createRequest(new CornProduct(), 148, meatChannel);


        try {
            meatChannel.makeReportFromChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nChainValidation");
        System.out.println(Chain.getInstance().BlockChaionValidation());


        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("reports/reportTransaction.txt"));
            writer.write("\nAll requests and responses in channels\n\n");

            List<Request> requests = new ArrayList<>();

            requests.addAll(meatChannel.getRequests());
            requests.addAll(meatChannel.getResponses());
            requests.addAll(otherChannel.getRequests());
            requests.addAll(otherChannel.getResponses());

            for (Request request : requests) {
                StringBuilder info = new StringBuilder();

                info.append("Channel ").append(request.getChannel().getClass().getName());
                info.append("\nSender ").append(request.getSender().getName());
                info.append("\nProduct ").append(request.getProduct().getName());
                info.append("\nQuantity ").append(request.getAmount());

                writer.write(info + "\n\n");
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
