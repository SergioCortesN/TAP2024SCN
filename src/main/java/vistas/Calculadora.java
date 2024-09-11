package vistas;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.LinkedList;
import java.util.Queue;

public class Calculadora extends Stage
{

    private Button[][] arBtns;
    private Button btnClear;
    private TextField txtPantalla;
    private GridPane gdpteclado;
    private VBox vBox;
    private Scene escena;
    private String[] strteclas = {"7","8","9","*","4","5","6","/","1","2","3","+","0",".","=","-"};
    private double n1;
    private double n2;
    private String operador="";
    private double resul;
    private boolean botonIgualPress=false;

    private void crearUI()
    {
        arBtns = new Button[4][4];
        txtPantalla = new TextField();
        txtPantalla.setAlignment(Pos.CENTER_RIGHT);
        txtPantalla.setEditable(false);
        gdpteclado = new GridPane();
        gdpteclado.setId("font-teclas");
        CrearTeclado();
        btnClear = new Button("C");
        btnClear.setOnAction(event -> clear(btnClear));
        btnClear.setId("font-clear");
        vBox = new VBox(txtPantalla, gdpteclado, btnClear);
        escena = new Scene(vBox, 400, 400);
        escena.getStylesheets().add(getClass().getResource("/styles/Calculadora.css").toString());

    }

    private void CrearTeclado()
    {
        for (int i = 0; i <arBtns.length ; i++)
        {
            for (int j = 0; j < arBtns.length ; j++)
            {
                arBtns[j][i] = new Button(strteclas[4*i+j]);
                arBtns[j][i].setPrefSize(100,100);
                int finalI = i;
                int finalJ = j;
                arBtns[j][i].setOnAction(event -> detectarTecla(strteclas[4* finalI + finalJ]));
                gdpteclado.add(arBtns[j][i], j, i);
            }
        }
    }

    public Calculadora()
    {
        crearUI();
        this.setTitle("Calculadora");
        this.setScene(escena);
        this.show();
    }

    private void detectarTecla(String tecla)
    {
        if (botonIgualPress)
        {
            txtPantalla.clear();
            botonIgualPress=false;
        }
        if (tecla.matches("[0-9.]")){
            txtPantalla.appendText(tecla);
        } else if (tecla.matches("[-+/*]")) {
            if (operador.isEmpty()) {
                operador = tecla;
                n1 = Double.parseDouble(txtPantalla.getText());
                txtPantalla.clear();
            } else if (!operador.isEmpty()) {
                n2 = Double.parseDouble(txtPantalla.getText());
                switch (operador) {
                    case "+":
                        resul = n1 + n2;
                        break;
                    case "-":
                        resul = n1 - n2;
                        break;
                    case "*":
                        resul = n1 * n2;
                        break;
                    case "/":
                        if (n2 == 0) {
                            txtPantalla.appendText("MATH ERROR");
                            break;
                        } else {
                            resul = n1 / n2;
                        }

                        break;
                }
                operador = tecla;
                n1 = resul;
                txtPantalla.clear();
            }
        } else if (tecla.matches("=")) {
            n2 = Double.parseDouble(txtPantalla.getText());
            switch (operador) {
                case "+":
                    resul = n1 + n2;
                    txtPantalla.setText(String.valueOf(resul));
                    break;
                case "-":
                    resul = n1 - n2;
                    txtPantalla.setText(String.valueOf(resul));
                    break;
                case "*":
                    resul = n1 * n2;
                    txtPantalla.setText(String.valueOf(resul));
                    break;
                case "/":
                    if(n2==0)
                    {
                        txtPantalla.setText("MATH ERROR");
                        break;
                    }
                    else
                    {
                        resul = n1 / n2;
                        txtPantalla.setText(String.valueOf(resul));
                    }
                    break;
            }
            resul=0;
            operador="";
            botonIgualPress=true;
        }

    }

        private void clear(Button btnClear)
        {
            resul=0;
            operador="";
            txtPantalla.clear();
        }

}

