package omo.channels;

import omo.blockchain.Transaction;
import omo.parties.*;

import java.io.IOException;
import java.util.List;

public abstract class Channel {

    public abstract void addRequest(Request request);

    public abstract void addMessage(String message);

    public abstract void addResponse(Request request);

    public abstract List<Request> getRequests();

    public abstract List<Request> getResponses();

    public abstract void setRequests(List<Request> requests);

    public abstract List<Party> getParticipants();

    public abstract void setParticipants(List<Party> participants);

    public abstract void makeReportFromChannel() throws IOException;

}
