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
        int[] result;

        int[] data = new int[6];
        data[0] = 1;

        hzResult.addToHzSearchResultList("RESULT-1");
        publisherFactory.publishSearchTopic(data);


        /*if (input.length == AppUtil.lengthOfData) {
            result = nodeFactory.searchWrapper(input);
            if (result == null) {
                outputTextarea.setText("Nothing found.");
            } else {
                outputTextarea.setText(AppUtil.intArrayToString(result));
            }
        } else {
            System.out.println("Invalid data provided.");
            inputField.setText("");
        }*/
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
}
