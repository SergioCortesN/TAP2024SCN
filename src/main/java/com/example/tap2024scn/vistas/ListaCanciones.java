package com.example.tap2024scn.vistas;

import com.example.tap2024scn.models.CancionDAO;
import components.ButtonCellCanciones;
import components.ButtonCellClientes;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.tap2024scn.models.ClienteDAO;
import javafx.util.Callback;

public class ListaCanciones extends Stage {

    private TableView<CancionDAO> tvCanciones;
    private ToolBar tblMenu;
    private VBox vBox;
    private Scene escena;

    public ListaCanciones() {
        CrearUI();
        this.setTitle("Lista de Canciones");
        this.setScene(escena);
        this.show();

    }

    private void CrearUI() {
        tblMenu = new ToolBar();
        ImageView imv = new ImageView(getClass().getResource("/images_loteria/next.png").toExternalForm());
        Button btnAddCte = new Button();
        btnAddCte.setOnAction(actionEvent -> new FormCanciones(tvCanciones, null));
        btnAddCte.setGraphic(imv);
        tblMenu.getItems().add(btnAddCte);

        tvCanciones = new TableView<CancionDAO>();
        CrearTable();
        vBox = new VBox(tblMenu, tvCanciones);
        escena = new Scene(vBox, 500, 250);

    }

    private void CrearTable() {
        CancionDAO objCte = new CancionDAO();

        TableColumn<CancionDAO, String> tcNombreCan = new TableColumn<>("Nombre Cancion");
        tcNombreCan.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<CancionDAO, String> tcDuracion = new TableColumn<>("Duracion");
        tcDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));

        TableColumn<CancionDAO, String> tcGenero = new TableColumn<>("Genero");
        tcGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));

        TableColumn<CancionDAO, String> tcFechaLanzada = new TableColumn<>("Fecha de Lanzamiento");
        tcFechaLanzada.setCellValueFactory(new PropertyValueFactory<>("fechalanzada"));

        TableColumn<CancionDAO, String> tcEditar = new TableColumn<>("");
        tcEditar.setCellFactory(new Callback<TableColumn<CancionDAO, String>, TableCell<CancionDAO, String>>() {
            @Override
            public TableCell<CancionDAO, String> call(TableColumn<CancionDAO, String> cancionDAOStringTableColumn) {
                return new ButtonCellCanciones("Editar");
            }
        });

        TableColumn<CancionDAO, String> tcBorrar = new TableColumn<>("");
        tcBorrar.setCellFactory(new Callback<TableColumn<CancionDAO, String>, TableCell<CancionDAO, String>>() {
            @Override
            public TableCell<CancionDAO, String> call(TableColumn<CancionDAO, String> cancionDAOStringTableColumn) {
                return new ButtonCellCanciones("Eliminar");
            }
        });

        tvCanciones.getColumns().addAll(tcNombreCan, tcDuracion , tcGenero, tcFechaLanzada, tcEditar, tcBorrar);
        tvCanciones.setItems(objCte.selectAll());


    }
}
