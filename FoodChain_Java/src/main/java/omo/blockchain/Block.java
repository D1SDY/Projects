package omo.blockchain;

import omo.channels.Request;

public class Block {
    private Request data;
    private Integer hash;
    private Integer previousHash;

    public Request getData() {
        return data;
    }

    public void setData(Request data) {
        this.data = data;
        this.hash = calcHash();
    }

    public Block(Request data, Integer previousHash) {
        this.data = data;
        if (data != null) {
            this.hash = calcHash();
        }
        this.previousHash = previousHash;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public Integer getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(Integer previousHash) {
        this.previousHash = previousHash;
    }


    public Integer calcHash() {
//        if (data == null)
//            return null;
//        return (data.getChannel().toString()+data.getSender().toString()+data.getProduct().toString()+data.getAmount()).toString().hashCode();
        if (data.getProduct() != null && data.getSender() != null && data.getChannel() != null) {
            return (data.getChannel().toString() + data.getSender().toString() + data.getProduct().toString() + data.getAmount()).toString().hashCode();
        }
        return null;
    }
}
