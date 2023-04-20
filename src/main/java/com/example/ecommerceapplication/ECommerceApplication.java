package com.example.ecommerceapplication;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;

public class ECommerceApplication extends Application {
    UserInterface userInterface = new UserInterface();
    @Override
    public void start(Stage stage) throws IOException {
        // Dock Icon setup
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        java.awt.Image image = defaultToolkit.getImage(getClass().getResource("/app_icon.png"));
        final Taskbar taskbar = Taskbar.getTaskbar();
        taskbar.setIconImage(image);

        Scene scene = new Scene(userInterface.createContent(), 320, 240);

        stage.setTitle("E-Commerce Application");
        stage.setWidth(960);
        stage.setHeight(540);
        stage.getIcons().add(new Image("file:/Users/ajay/IdeaProjects/E-Commerce-Application/src/main/resources/app_icon.png"));
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}