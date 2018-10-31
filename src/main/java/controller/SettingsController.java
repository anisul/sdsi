package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.AppUtil;



public class SettingsController {
    @FXML
    private Button settingSaveButton;

    @FXML
    private TextField lengthOfDataField;

    @FXML
    private TextField numberOfChunkField;

    @FXML
    private TextField numberOfPeerField;

    @FXML
    private TextField hammingDistanceField;

    @FXML
    private TextField binaryConversionThresholdField;

    @FXML
    private void initialize() {
        lengthOfDataField.setText(String.valueOf(AppUtil.lengthOfData));
        numberOfChunkField.setText(String.valueOf(AppUtil.chunkCount));
        numberOfPeerField.setText(String.valueOf(AppUtil.peerCount));
        hammingDistanceField.setText(String.valueOf(AppUtil.hammingThreshold));
        binaryConversionThresholdField.setText(String.valueOf(AppUtil.binaryConversionThreshold));
    }

    @FXML
    private void handleSaveButtonAction(ActionEvent e) {

    }
}
