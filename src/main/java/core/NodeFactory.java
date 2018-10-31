package core;

import types.Node;
import util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class NodeFactory {
    private List<Node> nodes = new ArrayList<Node>();

    public void initialize() {
        this.nodes.clear();
        for (int i = 0; i < AppUtil.peerCount; i++) {
            Node n = new Node(i);
            nodes.add(n);
        }
    }
}
