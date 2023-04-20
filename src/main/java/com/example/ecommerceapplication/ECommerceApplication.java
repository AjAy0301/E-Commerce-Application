package com.example.ecommerceapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Pane createContent(){
        Pane root = new Pane();
        return root; 
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent(), 320, 240);
        stage.setTitle("E-Commerce Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}