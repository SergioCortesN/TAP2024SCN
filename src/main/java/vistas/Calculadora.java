package vistas;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage
{

    private Button[][] arBtns;
    private TextField txtPantalla;
    private GridPane gdpteclado;
    private VBox vBox;
    private Scene escena;
    private String[] strteclas = {"7","8","9","*","4","5","6","/","1","2","3","+","0",".","=","-"};
    private void crearUI()
    {
        arBtns = new Button[4][4];
        txtPantalla = new TextField("0");
        txtPantalla.setAlignment(Pos.CENTER_RIGHT);
        txtPantalla.setEditable(false);
        gdpteclado = new GridPane();
        CrearTeclado();
        vBox = new VBox(txtPantalla, gdpteclado);
        escena = new Scene(vBox, 400, 400);
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
        txtPantalla.appendText(tecla);
    }
}
