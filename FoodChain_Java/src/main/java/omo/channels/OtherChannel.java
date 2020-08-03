package omo.channels;

import omo.blockchain.Transaction;
import omo.parties.Party;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OtherChannel extends Channel {

    private List<Request> requests = new ArrayList<>();
    private List<Request> responses = new ArrayList<>();

    public List<String> messages = new ArrayList<>();

    private List<Transaction> transactions = new ArrayList<>();

    private List<Party> participants = new ArrayList<>();
    private Handler handler= new Handler();

    public  void addRequest(Request request) {
        requests.add(request);
        handler.orderRequest(requests, participants);
    }

    @Override
    public void addMessage(String message) {
        messages.add(message);

    }

    public void addResponse(Request request){
        responses.add(request);
        handler.orderResponse(responses, participants);
    }

    public List<Request> getRequests() {
        return requests;
    }

    @Override
    public List<Request> getResponses() {
        return responses;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<Party> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Party> participants) {
        this.participants = participants;
    }

    public void makeReportFromChannel() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("reports/report.txt"));

        for (String message: messages)
        writer.write(message);

        writer.close();
    }
}
