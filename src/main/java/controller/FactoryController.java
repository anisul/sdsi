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

    @FXML
    private Button generateRndDataButton;

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
        long searchStartTime = System.currentTimeMillis();

        int[] input = AppUtil.stringToIntArray(inputField.getText());
        int sizeOfCluster = AppUtil.hazelcastInstance.getCluster().getMembers().size();
        boolean hasDiverged = false;

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
                    hasDiverged = true;
                    break;
                }
            } else if (hammingDistance == 0) {
                break;
            } else if (hammingDistance > AppUtil.searchConvergenceUpperBound) {
                hasDiverged = true;
                break;
            }
        }

        long searchFinishTime = System.currentTimeMillis();
        long searchTime = searchFinishTime - searchStartTime;
        if (!hasDiverged) {
            outputTextarea.setText("Search converged to: \n" + AppUtil.intArrayToString(AppUtil.binarization(result)) + "\n [time taken - " + String.valueOf(searchTime) + " ms]");
        } else {
            outputTextarea.setText("Search diverged." + "\n [time taken - " + String.valueOf(searchTime) + " ms]");
        }
    }

    @FXML
    private void handleStoreButtonAction(ActionEvent e) {
        int[] input = AppUtil.stringToIntArray(inputField.getText());

        if (input.length == AppUtil.lengthOfData) {
            publisherFactory.publishStoreTopic(input);
            outputTextarea.setText("Successfully stored data.");
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

    @FXML
    private void onGenerateRndDataClicked(ActionEvent e) {
        int[] input = AppUtil.generateBinaryRandomAddress(AppUtil.lengthOfData);
        inputField.setText("");
        inputField.setText(AppUtil.intArrayToString(input));
    }
}
