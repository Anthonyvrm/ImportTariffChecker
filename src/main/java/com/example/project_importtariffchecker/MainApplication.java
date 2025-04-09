package com.example.project_importtariffchecker;

import Database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Project Import Tariff Checker");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
         // Initialize the database when the application starts
    }
}
