package com.example.tap2024scn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private Button bt1, bt2, bt3;
    private VBox vBox;
    private HBox hBox;

    public void crearUI()
    {
        bt1 = new Button("BOTON 1");
        bt2 = new Button("BOTON 2");
        bt3 = new Button("BOTON 3");
        vBox = new VBox(bt1, bt2, bt3);
        hBox = new HBox(bt1, bt2, bt3);
    }

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        crearUI();
        Scene scene = new Scene(hBox, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}