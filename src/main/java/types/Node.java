package types;

import util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Bin> bins = new ArrayList<Bin>();
    private int id;

    public Node(int id) {
        this.id = id;
        for (int i = 0; i < AppUtil.chunkCount; i++) {
            Bin b = new Bin(id, i);
            bins.add(b);
        }
        System.out.println("Created " + AppUtil.chunkCount + " bins in node: " + this.id);
    }

    public List<Bin> getBins() {
        return bins;
    }

    public int getId() {
        return id;
    }
}
