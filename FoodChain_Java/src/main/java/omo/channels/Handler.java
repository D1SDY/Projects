package omo.channels;

import omo.parties.*;

import java.util.*;

class Handler {

    void orderRequest(List<Request> requests, List<Party> participants) {

        Request request = requests.get(requests.size() - 1);

        Party receiver;

        Party sender = request.getSender();

        if (sender.getClass() == Customer.class) {
            for (Party part : participants) {
                if (part.getClass() == Retailer.class) {
                    receiver = part;
                    receiver.acceptRequest(request, request.getSender());
                }
            }
        } else if (sender.getClass() == Retailer.class) {
            for (Party part : participants) {
                if (part.getClass() == Processor.class) {
                    receiver = part;
                    receiver.acceptRequest(request, request.getSender());
                }
            }
        } else if (sender.getClass() == Processor.class) {
            for (Party part : participants) {
                if (part.getClass() == Farmer.class) {
                    receiver = part;
                    receiver.acceptRequest(request, request.getSender());
                }
            }
        }
    }

    void orderResponse(List<Request> requests, List<Party> participants){
        Request backRequest = requests.get(requests.size() - 1);

        Party receiver;

        Party sender = backRequest.getSender();

        if (sender.getClass() == Farmer.class) {
            for (Party part : participants) {
                if (part.getClass() == Processor.class) {
                    receiver = part;
                    receiver.acceptResponse(backRequest, backRequest.getSender());
                }
            }
        } else if (sender.getClass() == Processor.class) {
            for (Party part : participants) {
                if (part.getClass() == Retailer.class) {
                    receiver = part;
                    receiver.acceptResponse(backRequest, backRequest.getSender());
                }
            }
        } else if (sender.getClass() == Retailer.class) {
            for (Party part : participants) {
                if (part.getClass() == Customer.class) {
                    receiver = part;
                    receiver.acceptResponse(backRequest, backRequest.getSender());
                }
            }
        }
    }
}
