package com.kodokushi.controller;

import com.kodokushi.crypto.Crypto;
import com.kodokushi.converter.Converter;

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
import java.util.ArrayList;

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
    private TextField keyTextField;
    @FXML
    private Button setKeyButton;
    @FXML
    private Button copyKeyButton;
    @FXML
    private ChoiceBox<String> algorithmNameChoice;
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
        StringSelection stringSelection = new StringSelection(string);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
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

    private boolean mValidateKeyTextField() {
        if (!keyTextField.getText().matches("(?=.*[\\d])(?=.*[\\s])(?=.*[a-fA-F])[a-fA-F\\s\\d]{1,48}")) {
            keyTextField.getStyleClass().add("error-border");
            return false;
        } else {
            keyTextField.getStyleClass().remove("error-border");
            return true;
        }
    }

    private boolean mValidateAlgorithmNameField() {
        if (!algorithmNameChoice.getValue().equals("AES") && !algorithmNameChoice.getValue().equals("XOR")) {
            algorithmNameChoice.getStyleClass().add("error-border");
            return false;
        } else {
            algorithmNameChoice.getStyleClass().remove("error-border");
            return true;
        }
    }

    private char[] mFillMessage(String textType, String algorithm) {
        if (textType.equals("HEX")) {
            if (algorithm.equals("AES")) {
                return Crypto.mPadMessage(Converter.mHexStringToCharArray(messageTextField.getText()));
            } else {
                return Converter.mHexStringToCharArray(messageTextField.getText());
            }
        } else {
            if (algorithm.equals("AES")) {
                return Crypto.mPadMessage(Converter.mStringToCharArray(messageTextField.getText()));
            } else {
                return Converter.mStringToCharArray(messageTextField.getText());
            }
        }
    }

    private void mEncryptDecryptAuxiliary(String mode) {
        boolean messageFieldStatus = mValidateMessageTextField();
        boolean keyFieldStatus = mValidateKeyTextField();
        boolean algorithmNameStatus = mValidateAlgorithmNameField();
        if (messageFieldStatus && keyFieldStatus && algorithmNameStatus) {
            Crypto crypto = new Crypto(algorithmNameChoice.getValue());
            char[] key = Converter.mHexStringToCharArray(keyTextField.getText());
            char[] message = mFillMessage(mGetToggleGroupValue(), algorithmNameChoice.getValue());
            switch (mode) {
                case "enc" -> crypto.mEncrypt(message, key);
                case "dec" -> crypto.mDecrypt(message, key);
            }
            resultTextField.setText(Converter.mCharArrayToString(message));
            resultHexTextField.setText(Converter.mCharArrayToHexString(message));
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

        keyTextField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                mValidateKeyTextField();
            }
        });

        algorithmNameChoice.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                mValidateAlgorithmNameField();
            }
        });

        setKeyButton.setOnAction(event -> keyTextField.setText(Converter.mCharArrayToHexString(Crypto.mSetKey())));

        copyKeyButton.setOnAction(event -> mCopyToClipboard(keyTextField.getText()));

        copyResultButton.setOnAction(event -> mCopyToClipboard(resultTextField.getText()));

        copyResultHexButton.setOnAction(event -> mCopyToClipboard(resultHexTextField.getText()));

        encryptButton.setOnAction(event -> mEncryptDecryptAuxiliary("enc"));

        decryptButton.setOnAction(event -> mEncryptDecryptAuxiliary("dec"));
    }
}