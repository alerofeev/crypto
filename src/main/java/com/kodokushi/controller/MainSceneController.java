package com.kodokushi.controller;

import com.kodokushi.converter.Converter;
import com.kodokushi.crypto.KeyGenerator;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class MainSceneController {

    @FXML
    private VBox mainScene;
    @FXML
    private MenuItem menuExitButton;
    @FXML
    private MenuItem menuManualButton;
    @FXML
    private TextField messageTextField;
    @FXML
    private ToggleGroup selectedMode;
    @FXML
    private ChoiceBox<String> algorithmChooser;
    @FXML
    private TextField keyTextField;
    @FXML
    private ChoiceBox<String> keySizeChooser;
    @FXML
    private Button setKeyButton;
    @FXML
    private Button copyKeyButton;
    @FXML
    private TextField resultTextField;
    @FXML
    private Button copyResultButton;
    @FXML
    private TextField resultHexTextField;
    @FXML
    private Button copyResultHexButton;
    @FXML
    private Button encryptButton;
    @FXML
    private Button decryptButton;

    private String mGetToggleGroupValue() {
        return ((RadioButton) selectedMode.getSelectedToggle()).getText();
    }

    private void mCopyToClipboard(String string) {
        if (string.length() != 0) {
            StringSelection stringSelection = new StringSelection(string);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        } else {
            throw new IllegalStateException("Zero string length");
        }
    }

    private boolean mValidateMessageTextField() {
        if (mGetToggleGroupValue().equals("HEX")) {
            if (!messageTextField.getText().matches("(?=.*[\\d])(?=.*[\\s])(?=.*[a-fA-F])[a-fA-F\\s\\d]+")) {
                messageTextField.getStyleClass().add("error-border");
                return false;
            } else {
                messageTextField.getStyleClass().remove("error-border");
                return true;
            }
        } else {
            if (messageTextField.getText().length() == 0) {
                messageTextField.getStyleClass().add("error-border");
                return false;
            } else {
                messageTextField.getStyleClass().remove("error-border");
                return true;
            }
        }
    }

    private boolean mValidateAlgorithmChooser() {
        if (!algorithmChooser.getValue().equals("AES ECB") && !algorithmChooser.getValue().equals("GOST")) {
            algorithmChooser.getStyleClass().add("error-border");
            return false;
        } else {
            algorithmChooser.getStyleClass().remove("error-border");
            return true;
        }
    }

    private boolean mValidateKeyTextField() {
        if (!keyTextField.getText().matches("(?=.*[\\d])(?=.*[\\s])(?=.*[a-fA-F])[a-fA-F\\s\\d]+")) {
            keyTextField.getStyleClass().add("error-border");
            return false;
        } else {
            keyTextField.getStyleClass().remove("error-border");
            return true;
        }
    }

    private void mEncryptDecryptAuxiliary(String mode) {
        if (mValidateMessageTextField() && mValidateKeyTextField() && mValidateAlgorithmChooser()) {

            resultTextField.setText("message");
            resultHexTextField.setText("message");
        } else {
            ErrorSceneController.mDisplayErrorWindow();
        }
    }

    private void mEncryptDecryptAuxiliary() {
        if (mValidateMessageTextField() && mValidateAlgorithmChooser() && mValidateKeyTextField()) {

        } else {
            ErrorSceneController.mDisplayErrorWindow();
        }
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> mainScene.requestFocus());
        mainScene.setOnMouseClicked(event -> mainScene.requestFocus());

        menuExitButton.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });

        menuManualButton.setOnAction(event -> ManualSceneController.mDisplayManualWindow());

        messageTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                mValidateMessageTextField();
            }
        });

        algorithmChooser.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (mValidateAlgorithmChooser()) {
                    switch(algorithmChooser.getValue()) {
                        case "AES ECB" -> {
                            keySizeChooser.setValue("128");
                            keySizeChooser.setDisable(false);
                        }
                        case "GOST" -> {
                            keySizeChooser.setValue("256");
                            keySizeChooser.setDisable(true);
                        }
                    }
                } else {
                    keySizeChooser.setDisable(true);
                }
            }
        });

        algorithmChooser.setOnHiding((event) -> mainScene.requestFocus());

        keyTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                mValidateKeyTextField();
            }
        });

        keySizeChooser.setOnHiding((event) -> mainScene.requestFocus());

        setKeyButton.setOnAction(event -> {
            keyTextField.setText(Converter.mCharArrayToHexString(KeyGenerator.mCreateKey(Integer.parseInt(
                    keySizeChooser.getValue()))));
            mainScene.requestFocus();
        });

        copyKeyButton.setOnAction(event -> {
            mCopyToClipboard(keyTextField.getText());
            mainScene.requestFocus();
        });

        copyResultButton.setOnAction(event -> {
            mCopyToClipboard(resultTextField.getText());
            mainScene.requestFocus();
        });

        copyResultHexButton.setOnAction(event -> {
            mCopyToClipboard(resultHexTextField.getText());
            mainScene.requestFocus();
        });

        encryptButton.setOnAction(event -> {
            // TODO: add algo selection
            mainScene.requestFocus();
        });

        decryptButton.setOnAction(event -> {
            // TODO: add algo selection
            mainScene.requestFocus();
        });
    }
}