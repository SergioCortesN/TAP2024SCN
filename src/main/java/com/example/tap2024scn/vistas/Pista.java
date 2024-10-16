package com.example.tap2024scn.vistas;
import components.CorredorThread;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Pista extends Stage {

    private String[] strCorredores = {"Juanpi", "Emilio", "Rafa", "Ian", "Sergio"};
    private GridPane gdppista;
    private ProgressBar[] pgbCorredores;
    private Button btnstart;
    private Label[] lblCorredores;
    private CorredorThread[] thdCorredores;
    private VBox vBox;
    private Scene escena;

    public Pista() {
        CrearUI();
        this.setTitle("Pista de Atletismo :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        lblCorredores = new Label[5];
        pgbCorredores = new ProgressBar[5];
        gdppista = new GridPane();
        for (int i = 0; i < 5; i++) {
            lblCorredores[i] = new Label(strCorredores[i]);
            gdppista.add(lblCorredores[i], 0, i);
            pgbCorredores[i] = new ProgressBar(0);
            gdppista.add(pgbCorredores[i], 1, i);

        }
        btnstart = new Button("Iniciar Carrera");
        btnstart.setOnAction(event -> IniciarCarrera());
        vBox = new VBox(gdppista, btnstart);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        escena = new Scene(vBox, 200, 150);
    }

    private void IniciarCarrera() {

        thdCorredores = new CorredorThread[5];
        for (int i = 0; i < 5; i++) {
            thdCorredores[i] = new CorredorThread(strCorredores[i], pgbCorredores[i]);
            thdCorredores[i].start();
        }
    }
}