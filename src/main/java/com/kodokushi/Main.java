package com.kodokushi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
// import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/mainScene.fxml")));
            // Font.loadFont(Objects.requireNonNull(getClass().getResource("/fonts/OpenSansLight.ttf")).toExternalForm(), 20);
            primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                    "/images/icon.png"))));
            primaryStage.setTitle("crypto");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}