package controller;

import com.hazelcast.core.HazelcastInstance;
import core.NodeFactory;
import core.PublisherFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import types.HzAppSetting;
import util.AppUtil;

import java.util.Map;


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

    HazelcastInstance hazelcastInstance = AppUtil.hazelcastInstance;

    PublisherFactory publisherFactory = new PublisherFactory();

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
        //Put updates to hazelcast appSetting map
        Map<String, String> hzAppSettingMap = hazelcastInstance.getMap("appSettingMap");
        hzAppSettingMap.put("lengthOfData", lengthOfDataField.getText());
        hzAppSettingMap.put("chunkCount", numberOfChunkField.getText());
        hzAppSettingMap.put("peerCount", numberOfPeerField.getText());
        hzAppSettingMap.put("hammingThreshold", hammingDistanceField.getText());
        hzAppSettingMap.put("binaryConversionThreshold", binaryConversionThresholdField.getText());
        hzAppSettingMap.put("searchConvergenceUpperBound", String.valueOf(Integer.parseInt(lengthOfDataField.getText())/2));

        //update AppUtil settings
        AppUtil.loadPropertiesFromHzMap();

        //Update in GUI
        lengthOfDataField.setText(String.valueOf(AppUtil.lengthOfData));
        numberOfChunkField.setText(String.valueOf(AppUtil.chunkCount));
        numberOfPeerField.setText(String.valueOf(AppUtil.peerCount));
        hammingDistanceField.setText(String.valueOf(AppUtil.hammingThreshold));
        binaryConversionThresholdField.setText(String.valueOf(AppUtil.binaryConversionThreshold));

        //publish the change event
        publisherFactory.publishSettingChangeTopic();
    }

    @FXML
    private void onRefreshSettingButtonClick(ActionEvent e) {
        lengthOfDataField.setText(String.valueOf(AppUtil.lengthOfData));
        numberOfChunkField.setText(String.valueOf(AppUtil.chunkCount));
        numberOfPeerField.setText(String.valueOf(AppUtil.peerCount));
        hammingDistanceField.setText(String.valueOf(AppUtil.hammingThreshold));
        binaryConversionThresholdField.setText(String.valueOf(AppUtil.binaryConversionThreshold));
    }
}
