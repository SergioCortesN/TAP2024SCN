package com.example.tap2024scn.vistas;

import com.example.tap2024scn.models.CancionDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.tap2024scn.models.ClienteDAO;

public class FormCanciones extends Stage
{

    private TextField txtnombre;
    private TextField txtduracion;
    private TextField txtgenero;
    private TextField txtfechalanzada;
    private Button btnGuerdar;
    private VBox vBox;
    private TableView<CancionDAO> tvCancion;

    private CancionDAO objCan;

    private Scene escena;

    public FormCanciones(TableView<CancionDAO> tbv, CancionDAO objCan)
    {
        this.tvCancion = tbv;

        CrearUI();
        if(objCan != null)
        {
            this.objCan = objCan;
            txtnombre.setText(this.objCan.getNombre());
            txtduracion.setText(String.valueOf(this.objCan.getDuracion()));
            txtgenero.setText(this.objCan.getGenero());
            txtfechalanzada.setText(this.objCan.getFechalanzada());

        }else {
            this.objCan = new CancionDAO();
        }
        this.setTitle("Agregar Cancion :D");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI()
    {
        txtnombre = new TextField();
        txtnombre.setPromptText("Cancion");
        txtduracion = new TextField();
        txtduracion.setPromptText("Duracion");
        txtgenero = new TextField();
        txtgenero.setPromptText("Genero");
        txtfechalanzada = new TextField();
        txtfechalanzada.setPromptText("Fecha de lanzamiento");
        btnGuerdar = new Button("Guardar");
        btnGuerdar.setOnAction(actionEvent -> GuardarCancion());
        vBox = new VBox(txtnombre, txtduracion, txtgenero, txtfechalanzada, btnGuerdar);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        escena = new Scene(vBox, 300, 200);

    }

    private void GuardarCancion()
    {
        objCan.setNombre(txtnombre.getText());
        objCan.setDuracion(Double.parseDouble(txtduracion.getText()));
        objCan.setGenero(txtgenero.getText());
        objCan.setFechalanzada(txtfechalanzada.getText());
        String msj;
        Alert.AlertType type;
        if (objCan.getIdcancion() > 0)
        {
            objCan.update();
        }else {
            if (objCan.insert() > 0){
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


        tvCancion.setItems(objCan.selectAll());
        tvCancion.refresh();
    }


}
