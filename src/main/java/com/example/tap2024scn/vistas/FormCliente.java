package com.example.tap2024scn.vistas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.tap2024scn.models.ClienteDAO;

public class FormCliente extends Stage
{

    private TextField txtnomcte;
    private TextField txtnumtel;
    private TextField txtemailcte;
    private Button btnGuerdar;
    private VBox vBox;
    private TableView<ClienteDAO> tvCliente;

    private ClienteDAO objCte;

    private Scene escena;

    public FormCliente(TableView<ClienteDAO> tbv, ClienteDAO objC)
    {
        this.tvCliente = tbv;

        CrearUI();
        if(objC != null)
        {
            this.objCte = objC;
            txtnomcte.setText(objCte.getNomcte());
            txtnumtel.setText(objCte.getNumtel());
            txtemailcte.setText(objCte.getEmailcte());
        }else {
            this.objCte = new ClienteDAO();
        }
        this.setTitle("Agregar Cliente :D");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI()
    {
        txtnomcte = new TextField();
        txtnomcte.setPromptText("Nombre del cliente");
        txtnumtel = new TextField();
        txtnumtel.setPromptText("Numero de telefono");
        txtemailcte = new TextField();
        txtemailcte.setPromptText("Email del cliente");
        btnGuerdar = new Button("Guardar");
        btnGuerdar.setOnAction(actionEvent -> GuardarCliente());
        vBox = new VBox(txtnomcte, txtnumtel, txtemailcte, btnGuerdar);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        escena = new Scene(vBox, 300, 150);

    }

    private void GuardarCliente()
    {
        objCte.setNomcte(txtnomcte.getText());
        objCte.setNumtel(txtnumtel.getText());
        objCte.setEmailcte(txtemailcte.getText());
        String msj;
        Alert.AlertType type;
        if (objCte.getId_cliente() > 0)
        {
            objCte.update();
        }else {
            if (objCte.insert() > 0){
                msj = "REGISTRO INSERTADO";
                type = Alert.AlertType.INFORMATION;
            }else {
                msj = "OCURRIO UN ERROR AL INSERTAR, INTENTE DE NUEVO";
                type = Alert.AlertType.ERROR;
            }
            Alert alerta = new Alert(type);
            alerta.setTitle("MENSAJE DEL SISTEMA :)");
            alerta.setContentText(msj);
            alerta.showAndWait();
        }


        tvCliente.setItems(objCte.selectAll());
        tvCliente.refresh();
    }


}
