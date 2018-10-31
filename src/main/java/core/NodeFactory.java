package core;

import types.Bin;
import types.Node;
import util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class NodeFactory {
    private static List<Node> nodes = new ArrayList<Node>();

    /*
    * Initializes nodes with bins (bins have random addresses while being generated)
    * */
    public void initialize() {
        this.nodes.clear();
        for (int i = 0; i < AppUtil.peerCount; i++) {
            Node n = new Node(i);
            nodes.add(n);
        }
        System.out.println("Created " + AppUtil.peerCount + " nodes.");
    }

    /*
    * Store data in bins comparing the hamming distance.
    */
    public void store(int[] input) {
        for (Node node : nodes) {
            for (Bin bin : node.getBins()) {
                if (AppUtil.calculateHammingDistance(bin.getAddress(), input) < AppUtil.hammingThreshold) {
                    bin.setData(AppUtil.sumTwoEntryBitwise(bin.getData(), AppUtil.bipolarConversion(input)) );
                }
            }
        }
    }

    /**
     * Searches inside all the bins in all the nodes
     * @param input searching data
     * @return searched address
     */
    public int[] search(int[] input) {
        int[] result = new int[input.length];

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.get(i).getBins().size(); j++) {
                if (AppUtil.calculateHammingDistance(nodes.get(i).getBins().get(j).getAddress(), input) < AppUtil.hammingThreshold) {
                    result = AppUtil.sumTwoEntryBitwise(result, nodes.get(i).getBins().get(i).getAddress());
                }
            }
        }
        return result;
    }

    /**
     * Iteratively searches inside all the bins in all the nodes
     * @param input searching data
     * @return reverse bi-polarized search result
     */
    public int[] searchWrapper(int[] input) {
        int[] result;
        result = search(input);

        do {
            result = search(result);
            if (AppUtil.calculateHammingDistance(input, result) == 0) {
                break;
            } else if (AppUtil.calculateHammingDistance(input, result) < 0
                    || AppUtil.calculateHammingDistance(input, result) == AppUtil.searchConvergenceUpperBound) {
                System.out.println("Search diverges!");
                break;
            }
        } while (AppUtil.calculateHammingDistance(input, result) > 0
                && AppUtil.calculateHammingDistance(input, result) < AppUtil.searchConvergenceUpperBound);

        return AppUtil.reverseBipolarConversion(result);
    }

    /**
     * Returns list of nodes in member
     * @return searched addresses
     */
    public static List<Node> getNodes() {
        return nodes;
    }

    /**
     * Prints all the node with bins' detail
     */
    public void printAllNodes() {
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println("In Node-" + nodes.get(i).getId() + ": ") ;
            for (int j = 0; j < nodes.get(i).getBins().size(); j++) {
                nodes.get(i).getBins().get(j).printBin();
            }
        }
    }
}
