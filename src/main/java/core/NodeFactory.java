package core;

import types.Bin;
import types.Node;
import util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class NodeFactory {
    private static List<Node> nodes = new ArrayList<Node>();

    /**
     * Initializes nodes with bins (bins have random addresses while being generated)
     */
    public void initialize() {
        this.nodes.clear();
        for (int i = 0; i < AppUtil.peerCount; i++) {
            Node n = new Node(i);
            nodes.add(n);
        }
        System.out.println("Created " + AppUtil.peerCount + " nodes.");
    }

    /**
     * Store data in bins comparing the hamming distance.
     */
    public void store(int[] input) {
        for (Node node : nodes) {
            for (Bin bin : node.getBins()) {
                int hammingDistance = AppUtil.calculateHammingDistance(bin.getAddress(), input);
                if (hammingDistance < AppUtil.hammingThreshold) {
                    int[] bipolarData = AppUtil.bipolarConversion(input);
                    int[] dataToSet = AppUtil.sumTwoEntryBitwise(bin.getData(), bipolarData, true);
                    bin.setData(dataToSet);
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
        int[] result = null;

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.get(i).getBins().size(); j++) {
                int hammingDistance = AppUtil.calculateHammingDistance(nodes.get(i).getBins().get(j).getAddress(), input);
                if (hammingDistance < AppUtil.hammingThreshold) {
                    if (result == null) {
                        result = new int[input.length];
                    }
                    result = AppUtil.sumTwoEntryBitwise(result, nodes.get(i).getBins().get(j).getData(), false);
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
    /*public int[] searchWrapper(int[] input) {
        int[] result;
        result = search(input);
        if (result == null) {
            return null;
        }
        AppUtil.printIntArray(result);
        int terminationFlag = 0;
        int hammingDistance;
        int prevHammingDistance;

        hammingDistance = AppUtil.calculateHammingDistance(input, AppUtil.binarization(result));

        while (hammingDistance > 0 && hammingDistance <= AppUtil.searchConvergenceUpperBound) {
            int[] prevResult = AppUtil.copyArray(result);
            result = search(AppUtil.binarization(result));
            if (result == null) {
                return null;
            }
            prevHammingDistance = hammingDistance;
            hammingDistance = AppUtil.calculateHammingDistance(AppUtil.binarization(prevResult)
                    , AppUtil.binarization(result));

            if (prevHammingDistance == hammingDistance) {
                terminationFlag++;
                if (terminationFlag > 100) {
                    System.out.println("Search Diverged.");
                    break;
                }
            } else if (hammingDistance == 0) {
                System.out.println("Search converged");
                break;
            } else if (hammingDistance > AppUtil.searchConvergenceUpperBound) {
                System.out.println("Search Diverged.");
                break;
            }
        }

        //System.out.println("Search converge.");
        return AppUtil.binarization(result);
    }*/

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
    public StringBuffer printAllNodes() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < nodes.size(); i++) {
            sb.append("In Node-" + nodes.get(i).getId() + ": \n");
            for (int j = 0; j < nodes.get(i).getBins().size(); j++) {
                sb.append(nodes.get(i).getBins().get(j).printBin());
            }
        }
        return sb;
    }

    /**
     * Prints all the node with bins' detail
     */
    public void printAllNodesInConsole() {
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println("In Node-" + nodes.get(i).getId() + ": ") ;
            for (int j = 0; j < nodes.get(i).getBins().size(); j++) {
                nodes.get(i).getBins().get(j).printBinInConsole();
            }
        }
    }
}
