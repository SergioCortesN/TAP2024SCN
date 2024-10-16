package com.example.tap2024scn;

import com.example.tap2024scn.vistas.*;
import components.CorredorThread;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.example.tap2024scn.models.Conexion;

import java.io.IOException;

public class HelloApplication extends Application {

    private BorderPane bdpPrincipal;
    private MenuBar mnbPrincipal;
    private Menu menCompetencia1, menCompetencia2, menCompetencia3;
    private MenuItem mitCalculadora, mitLoteria, mitMusica, mitBuscaMinas, mitCarreras;
    public void crearUI()
    {
        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction(event -> new Calculadora());
        mitLoteria = new MenuItem("Loteria");
        mitLoteria.setOnAction(event -> new Loteria());
        mitMusica = new MenuItem("Musica");
        mitMusica.setOnAction(actionEvent -> new ListaClientes());
        mitBuscaMinas = new MenuItem("Buscaminas");
        mitBuscaMinas.setOnAction(actionEvent -> new Buscaminas());
        menCompetencia1 = new Menu("Competencia1");
        menCompetencia1.getItems().addAll(mitCalculadora, mitLoteria, mitMusica, mitBuscaMinas);

        mitCarreras = new MenuItem("Carreras");
        mitCarreras.setOnAction(actionevent -> new Pista());
        menCompetencia2 = new Menu("Competencia2");
        menCompetencia2.getItems().addAll(mitCarreras);
        mnbPrincipal = new MenuBar(menCompetencia1, menCompetencia2);
        bdpPrincipal = new BorderPane();
        bdpPrincipal.setTop(mnbPrincipal);
    }

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        crearUI();
        Scene scene = new Scene(bdpPrincipal,320,240);
        scene.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());
        stage.setTitle("");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        Conexion.CrearConexion();

    }

    public static void main(String... args) {
        launch();
    }
}