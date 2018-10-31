package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    private void initialize() {
        outputTextarea.appendText("Intializing.....\n");
        outputTextarea.appendText("System is ready to perform.\n");
    }


    @FXML
    private void handleSearchButtonAction(ActionEvent e) {

    }

    @FXML
    private void handleStoreButtonAction(ActionEvent e) {

    }
}
