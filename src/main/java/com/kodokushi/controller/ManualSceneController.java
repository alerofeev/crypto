package com.kodokushi.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ManualSceneController {

    @FXML
    private VBox mainScene;
    @FXML
    private Button closeButton;

    public static void mDisplayManualWindow() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(ManualSceneController.class.getResource(
                    "/view/manualScene.fxml")));
            Stage stage = new Stage();
            stage.getIcons().add(new Image(Objects.requireNonNull(ManualSceneController.class.getResourceAsStream(
                    "/images/icon.png"))));
            stage.setTitle(new String(("О программе crypto").getBytes("Windows-1251"),
                    "Windows-1251"));
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> mainScene.requestFocus());
        mainScene.setOnMouseClicked(event -> mainScene.requestFocus());

        closeButton.setOnAction(event -> ((Stage) ((Node) event.getSource()).getScene().getWindow()).close());
    }
}