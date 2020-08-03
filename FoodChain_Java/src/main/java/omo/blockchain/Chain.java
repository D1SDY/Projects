package omo.blockchain;

import omo.channels.Request;
import omo.products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Chain {

    private static Chain instance;
    private Product product;

    private Chain() {
    }

    ;

    List<Block> blocks = new ArrayList<>();

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public void createGenesisBlock() {
        Block block = new Block(new Request(null, null, 0, null), 0);
        block.setPreviousHash(null);
        blocks.add(block);
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean BlockChaionValidation() {
        int size = blocks.size();
        boolean temp = false;

        for (int i = 1; i < size; i++) {
            if (blocks.get(i).getData() != null) {
                if (blocks.get(i - 1).getData() != null) {
                    temp = Objects.equals(blocks.get(i).getPreviousHash(), blocks.get(i - 1).getHash());
                } else
                    temp = true;
            }
        }
        return temp;
    }

    public static Chain getInstance() {
        if (instance == null)
            instance = new Chain();
        return instance;
    }

    public Integer getPrevBlockHash() {
        return blocks.get(blocks.size() - 1).getHash();
    }

}
