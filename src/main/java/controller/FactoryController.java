package controller;

import core.NodeFactory;
import core.PublisherFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import types.HzResult;
import util.AppUtil;

public class FactoryController {
    @FXML
    private Button storeButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField inputField;

    @FXML
    private TextArea outputTextarea;

    NodeFactory nodeFactory = new NodeFactory();
    PublisherFactory publisherFactory = new PublisherFactory();
    HzResult hzResult = new HzResult();

    @FXML
    private void initialize() {
        outputTextarea.appendText("Initializing.....\n");
        outputTextarea.appendText("System is ready to perform.\n");
    }

    @FXML
    private void handleSearchButtonAction(ActionEvent e) {
        int[] input = AppUtil.stringToIntArray(inputField.getText());
        int sizeOfCluster = AppUtil.hazelcastInstance.getCluster().getMembers().size();

        publisherFactory.publishSearchTopic(input);
        while (true) {
            //wait for search results to arrive in distributed memory space
            if (hzResult.getHzSearchResultList().size() == sizeOfCluster) {
                break;
            }
        }

        int[] result = getCumulatedResultFromHzResultSet();

        int hammingDistance = AppUtil.calculateHammingDistance(AppUtil.binarization(result), input);
        int previousHammingDistance;
        int terminationFlag = 0;

        while (hammingDistance > 0 && hammingDistance < AppUtil.searchConvergenceUpperBound) {
            int[] previousResult = AppUtil.copyArray(result);
            publisherFactory.publishSearchTopic(AppUtil.binarization(result));
            while (true) {
                //wait for search results to arrive in distributed memory space
                if (hzResult.getHzSearchResultList().size() == sizeOfCluster) {
                    break;
                }
            }
            result = getCumulatedResultFromHzResultSet();
            previousHammingDistance = hammingDistance;
            hammingDistance = AppUtil.calculateHammingDistance(AppUtil.binarization(result)
                    , AppUtil.binarization(previousResult));

            if (previousHammingDistance == hammingDistance) {
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

        outputTextarea.setText(AppUtil.intArrayToString(AppUtil.binarization(result)));
    }

    @FXML
    private void handleStoreButtonAction(ActionEvent e) {
        int[] input = AppUtil.stringToIntArray(inputField.getText());

        if (input.length == AppUtil.lengthOfData) {
            publisherFactory.publishStoreTopic(input);
            outputTextarea.setText(nodeFactory.printAllNodes().toString());
        } else {
            System.out.println("Invalid data provided.");
            inputField.setText("");
        }
    }

    private  int[] getCumulatedResultFromHzResultSet() {
        int sizeOfCluster = AppUtil.hazelcastInstance.getCluster().getMembers().size();
        int [][] cumulatedResultArray = new int[sizeOfCluster][AppUtil.lengthOfData];

        for (int i = 0; i < hzResult.getHzSearchResultList().size(); i++) {
            String hzListEntry = hzResult.getHzSearchResultList().get(i);
            cumulatedResultArray[i] = AppUtil.commaSeparatedStringToIntArray(hzListEntry);
        }

        hzResult.getHzSearchResultList().clear();
        return AppUtil.sumMemberResultBitwise(cumulatedResultArray, AppUtil.lengthOfData);
    }
}
