package vistas;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage
{

    private Button[] arBtns;
    private TextField txtPantalla;
    private GridPane teclado;
    private VBox vBox;
    private Scene escena;

    private void crearUI()
    {
        arBtns = new Button[16];
        txtPantalla = new TextField("0");
        teclado = new GridPane();
        vBox = new VBox();
        escena = new Scene();
    }

    public Calculadora()
    {
        this.setTitle("Calculadora");
        this.setScene();
        this.show();
    }
}
