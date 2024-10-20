package com.example.tap2024scn.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Musica extends Stage
{
    private Label manejar;
    private Button btnClientes, btnCancion;
    private VBox Vbox;
    private Scene escena;

    public Musica(){
        CrearUI();
        this.setTitle("Base de Datos Musica");
        this.setScene(escena);
        this.show();

    }

    private void CrearUI()
    {
        manejar = new Label("BIENVENIDO A LA BASE DE DATOS \n SELECCIONE LOS DATOS A VIZUALIZAR");
        btnClientes = new Button("Clientes");
        btnClientes.setOnAction(e ->new ListaClientes());
        btnCancion = new Button("Canciones");
        btnCancion.setOnAction(e ->new ListaCanciones());
        Vbox = new VBox(manejar, btnClientes, btnCancion);
        escena = new Scene(Vbox, 500, 500);

    }
}
