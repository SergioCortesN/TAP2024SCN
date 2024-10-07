package vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.ClienteDAO;

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
        tvClientes = new TableView<>();
        vBox = new VBox(tblMenu, tvClientes);
        escena = new Scene(vBox, 500,250);

    }
}
