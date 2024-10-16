package com.example.tap2024scn.vistas;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.util.*;
import javax.swing.*;

public class Buscaminas extends Stage
{
    //preferencias
    private TextField txtnumeroMinas;
    private TextField txttamañoCuadricula;
    private Button btniniciar;
    private HBox hbPar;
    private boolean poderPonerBand = true;


    //juego
    private int cantMinas;
    private int cantBand;
    private int tamañoCuadricula;
    private boolean minaencontrada = false;
    private String[][] Minas;
    private Button[][] btnTablero;
    private GridPane gdpTablero;
    private VBox vbPrincipal;
    private Scene escena;

    private void CrearUI(GridPane gdpTablero) {
        gdpTablero.getChildren().clear();
        btnTablero = new Button[tamañoCuadricula][tamañoCuadricula];
        Minas = new String[tamañoCuadricula][tamañoCuadricula];
        tableroMinas();
        crearTablero();

    }

    public Buscaminas(){
        vbPrincipal = new VBox();
        gdpTablero = new GridPane();

        txtnumeroMinas = new TextField();
        txtnumeroMinas.setPromptText("Numero de Minas");

        txttamañoCuadricula = new TextField();
        txttamañoCuadricula.setPromptText("Tamaño de Cuadricula");

        btniniciar = new Button("Iniciar");

        btniniciar.setId("btniniciar");
        btniniciar.setOnAction(event -> {
            try {
                cantMinas = Integer.parseInt(txtnumeroMinas.getText());
                cantBand = cantMinas;
                tamañoCuadricula = Integer.parseInt(txttamañoCuadricula.getText());
                if(tamañoCuadricula>15){
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText(null);
                    alerta.setContentText("LA CUADRICULA ES DEMASIADO GRANDE PARA SU JUGABILIDAD");
                    alerta.showAndWait();
                    return;
                }
                int tam= tamañoCuadricula*tamañoCuadricula;
                if(cantMinas > tam*(0.2)){
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText(null);
                    alerta.setContentText("LA CANTIDAD DE MINAS ES DEMASIADO GRANDE PARA SU JUGABILIDAD");
                    alerta.showAndWait();
                    return;
                }
                CrearUI(gdpTablero);
                minaencontrada = false;
                poderPonerBand = true;
            }catch (Exception e){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("NUMERO NO VALIDO");
                alerta.setHeaderText(null);
                alerta.setContentText("FAVOR DE INGRESAR SOLAMENTE NUMEROS ENTEROS");
                alerta.showAndWait();
            }
        });
        gdpTablero.setAlignment(Pos.CENTER);
        hbPar = new HBox(txtnumeroMinas,txttamañoCuadricula,btniniciar);
        hbPar.setAlignment(Pos.CENTER);
        vbPrincipal.getChildren().addAll(hbPar,gdpTablero);
        escena = new Scene(vbPrincipal,400,500);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        escena.getStylesheets().add(getClass().getResource("/styles/Buscaminas.css").toString());
        this.setTitle("Buscaminas");
        this.getIcons().add(new Image(getClass().getResource("/images_BusMinas/bomba.png").toExternalForm()));
        this.setScene(escena);
        this.setMaximized(true);
        this.show();
    }

    private void crearTablero()
    {
        int contM = 0;
        for (int i = 0; i < btnTablero.length; i++) {
            for (int j = 0; j < btnTablero.length; j++) {
                btnTablero[j][i] = new Button();
                btnTablero[j][i].setUserData(new int[] {j,i});;
                btnTablero[j][i].setPrefSize(50,50);
                int inicio=j;
                int fin=i;
                btnTablero[j][i].setOnAction(e -> desbloquearCasilla(btnTablero[inicio][fin]));

                gdpTablero.add(btnTablero[j][i], j, i);
                if(contM < cantMinas){
                    Minas[i][j] = "x";
                    contM++;
                }
                if (contM == cantMinas) {
                    revolverMinas();
                    contM++;
                }

                btnTablero[j][i].setOnMouseClicked(mouseEvent -> {
                    if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                        if(!poderPonerBand){
                            return;
                        }
                        if(!btnTablero[inicio][fin].getStyle().contains("background-color: beige" ) ){
                            if (btnTablero[inicio][fin].getGraphic() == null && cantBand >0 && cantBand <= cantMinas) {
                                ImageView ivBand = new ImageView(getClass().getResource("/images_BusMinas/band.png").toExternalForm());
                                ivBand.setFitHeight(20);
                                ivBand.setFitWidth(20);
                                btnTablero[inicio][fin].setGraphic(ivBand);
                                cantBand--;
                            } else if (btnTablero[inicio][fin].getGraphic() != null) {
                                btnTablero[inicio][fin].setGraphic(null);
                                cantBand++;
                            }
                        }
                    }
                });

            }
        }
    }

    private void desbloquearCasilla(Button casilla)
    {
        if(!minaencontrada)
        {
            int [] posicion = (int[]) casilla.getUserData();
            int columna = posicion[0];
            int fila = posicion[1];
            if(btnTablero[columna][fila].getGraphic() == null) {
                if(Minas[fila][columna].equals("x"))
                {
                    ImageView ivBomba = new ImageView(getClass().getResource("/images_BusMinas/bomba.png").toExternalForm());
                    ivBomba.setFitHeight(20);
                    ivBomba.setFitWidth(20);
                    casilla.setGraphic(ivBomba);
                    casilla.getStyleClass().add("bomb-button");
                    busquedaMina();
                    minaencontrada = true;
                    poderPonerBand = false;
                    JOptionPane.showMessageDialog(null,"¡LO SENTIMOS, AUN NO LE SABES, SUERTE :3!");
                }else {
                    desbloquear(fila, columna);
                    int gano=contar0();
                    if (gano == 0){
                        minaencontrada = true;
                        poderPonerBand = false;
                        JOptionPane.showMessageDialog(null,"¡FELICITACIONES, ERES EL REY DEL BUSCAMINAS :D!");

                    }
                }
            }
        }
    }

    private void busquedaMina(){
        for (int i = 0; i < Minas.length; i++) {
            for (int j = 0; j < Minas[i].length; j++) {
                if(Minas[i][j].equals("x")){
                    ImageView ivBomba = new ImageView(getClass().getResource("/images_BusMinas/bomba.png").toExternalForm());
                    ivBomba.setFitHeight(20);
                    ivBomba.setFitWidth(20);
                    btnTablero[j][i].setGraphic(ivBomba);
                    btnTablero[j][i].getStyleClass().add("bomb-button");
                }
            }
        }
    }

    private void tableroMinas()
    {
        for (int i = 0; i < Minas.length; i++) {
            for (int j = 0; j < Minas[i].length; j++) {
                Minas[i][j] = "";
            }
        }
    }

    private void revolverMinas()
    {
        LinkedList <String> minas = new LinkedList();
        for (int i = 0; i < Minas.length; i++) {
            for (int j = 0; j < Minas.length; j++) {
                minas.add(Minas[i][j]);
            }

        }

        Collections.shuffle(minas);

        for (int i = 0; i < Minas.length; i++) {
            for (int j = 0; j < Minas.length; j++) {
                Minas[i][j] = minas.removeFirst();
            }

        }

        for (int i = 0; i < Minas.length; i++) {
            for (int j = 0; j < Minas.length; j++) {
                if (Minas[i][j].isEmpty()) {
                    Minas[i][j] = "0";
                }
            }

        }
    }

    private void desbloquear(int fila, int columna)
    {
        int minas = contarMinas(fila, columna);
        if(Minas[fila][columna].equals("0") && btnTablero[columna][fila].getGraphic() == null)
        {
            if (minas>0) {
                Minas[fila][columna] = "s";
                btnTablero[columna][fila].setStyle("-fx-background-color: beige");
                btnTablero[columna][fila].setText(String.valueOf(minas));
                btnTablero[columna][fila].setTextFill(Color.CORAL);
            } else if(minas==0){
                Minas[fila][columna] = "s";
                btnTablero[columna][fila].setStyle("-fx-background-color: beige");
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int filaV = fila + i;
                        int columnaV = columna + j;
                        if (filaV >= 0 && filaV < Minas.length && columnaV >= 0 && columnaV < Minas[0].length) {
                            desbloquear(filaV, columnaV);
                        }
                    }
                }
            }
        }
    }

    private int contarMinas(int fila, int columna){
        int numero=0;
        int cont=1;
        while (cont < 9){
            if(cont==1){
                if (columna+1 < Minas.length && Minas[fila][columna+1].equals("x"))
                {
                    numero++;
                    cont++;
                }
            } else if (cont==2) {
                if (columna+1 < Minas.length && fila-1 >= 0 && Minas[fila-1][columna+1].equals("x"))
                {
                    numero++;
                }
            }else if (cont==3) {
                if ( fila-1 >= 0 && Minas[fila-1][columna].equals("x"))
                {
                    numero++;
                }
            }else if (cont==4) {
                if ( columna-1 >= 0 && fila-1 >= 0 && Minas[fila-1][columna-1].equals("x"))
                {
                    numero++;
                }
            }else if (cont==5) {
                if ( columna-1 >= 0 && Minas[fila][columna-1].equals("x"))
                {
                    numero++;
                }
            }else if (cont==6) {
                if ( columna-1 >= 0 && fila+1 < Minas.length && Minas[fila+1][columna-1].equals("x"))
                {
                    numero++;
                }
            }else if (cont==7) {
                if ( fila+1 < Minas.length && Minas[fila+1][columna].equals("x"))
                {
                    numero++;
                }
            }else if (cont==8) {
                if ( columna+1 < Minas.length && fila+1 < Minas.length && Minas[fila+1][columna+1].equals("x"))
                {
                    numero++;
                }
            }

            cont++;
        }
        return numero;
    }

    private int contar0(){
        int ceros=0;
        for (int i = 0; i < Minas.length; i++) {
            for (int j = 0; j < Minas[i].length; j++) {
                if (Minas[i][j].equals("0")) {
                    ceros++;
                }
            }
        }
        return ceros;
    }
}