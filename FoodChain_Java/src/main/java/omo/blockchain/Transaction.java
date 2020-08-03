package omo.blockchain;

import omo.parties.Party;

public class Transaction {

    private Party party;
    private String info;
    private Transaction prevTransaction;

    public Transaction(Party party, String info, Transaction prevTransaction) {
        this.party = party;
        this.info = info;
        this.prevTransaction = prevTransaction;
    }
}
