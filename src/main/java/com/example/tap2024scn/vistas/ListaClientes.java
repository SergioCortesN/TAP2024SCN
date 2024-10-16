package com.example.tap2024scn.vistas;

import components.ButtonCell;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.tap2024scn.models.ClienteDAO;
import javafx.util.Callback;

public class ListaClientes extends Stage {

    private TableView<ClienteDAO> tvClientes;
    private ToolBar tblMenu;
    private VBox vBox;
    private Scene escena;

    public ListaClientes() {
        CrearUI();
        this.setTitle("Lista de Clientes");
        this.setScene(escena);
        this.show();

    }

    private void CrearUI() {
        tblMenu = new ToolBar();
        ImageView imv = new ImageView(getClass().getResource("/images_loteria/next.png").toExternalForm());
        Button btnAddCte = new Button();
        btnAddCte.setOnAction(actionEvent -> new FormCliente(tvClientes, null));
        btnAddCte.setGraphic(imv);
        tblMenu.getItems().add(btnAddCte);

        tvClientes = new TableView<ClienteDAO>();
        CrearTable();
        vBox = new VBox(tblMenu, tvClientes);
        escena = new Scene(vBox, 500,250);

    }

    private void CrearTable()
    {
        ClienteDAO objCte = new ClienteDAO();

        TableColumn <ClienteDAO, String> tcNomCte = new TableColumn<>("Nombre Cliente");
        tcNomCte.setCellValueFactory(new PropertyValueFactory<>("nomcte"));

        TableColumn <ClienteDAO, String> tcNumTel = new TableColumn<>("Telefono");
        tcNumTel.setCellValueFactory(new PropertyValueFactory<>("numtel"));

        TableColumn <ClienteDAO, String> tcEmailCte = new TableColumn<>("Email");
        tcEmailCte.setCellValueFactory(new PropertyValueFactory<>("emailcte"));

        TableColumn <ClienteDAO, String> tcEditar = new TableColumn<>("");
        tcEditar.setCellFactory(new Callback<TableColumn<ClienteDAO, String>, TableCell<ClienteDAO, String>>() {
            @Override
            public TableCell<ClienteDAO, String> call(TableColumn<ClienteDAO, String> clienteDAOStringTableColumn) {
                return new ButtonCell("Editar");
            }
        });

        TableColumn <ClienteDAO, String> tcBorrar = new TableColumn<>("");
        tcBorrar.setCellFactory(new Callback<TableColumn<ClienteDAO, String>, TableCell<ClienteDAO, String>>() {
            @Override
            public TableCell<ClienteDAO, String> call(TableColumn<ClienteDAO, String> clienteDAOStringTableColumn) {
                return new ButtonCell("Eliminar");
            }
        });

        tvClientes.getColumns().addAll(tcNomCte, tcNumTel, tcEmailCte, tcEditar, tcBorrar);
        tvClientes.setItems(objCte.selectAll());


    }
}
