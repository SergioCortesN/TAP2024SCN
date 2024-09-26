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
    //DECLARACION DE TODAS LAS VARIABLES A UTILIZAR
    private Button[][] arBtns; //ARREGLO PARA ALMACENAR LOS BOTONES DE LA CALCULADORA
    private Button btnClear; //BOTON DE LIMPIADO
    private TextField txtPantalla; //CAJA DE TEXTO PARA LA PANTALLA
    private GridPane gdpteclado; //ALMACENA LOS BOTONES PARA EL TECLADO DE LA CALCULADORA
    private VBox vBox; //PERMITE QUE LOS OBJETOS UTILIZADOS SE ORGANICEN DE MANERA VERTICAL
    private Scene escena; //ALMACENA LA INTERFAZ
    private String[] strteclas = {"7","8","9","*","4","5","6","/","1","2","3","+","0",".","=","-"}; //ARREGLO PARA NOMBRAR A LOS BOTONES
    //VARIABLES PARA REALIZAR OPERACIONES CON LA CALCULADORA
    private double n1;
    private double n2;
    private String operador="";
    private double resul;
    private boolean botonIgualPress=false;
    private boolean solomenosenpantalla;
    //CREACION DE LA INTERFAZ, AQUI SE DECLARAN TODAS LAS VARIABLES UTILIZADAS
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
    //METODO QUE PERMITE LA CREACION DEL TECLADO
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
    //CONSTRUCTOR QUE PERMITE LA VISUALIZACION DE LA CALCULADORA
    public Calculadora()
    {
        crearUI();
        this.setTitle("Calculadora");
        this.setScene(escena);
        this.show();
    }
    //METODO PARA DARLE FUNCIONALIDAD A LA CALCULADORA
    private void detectarTecla(String tecla)
    {
        if(txtPantalla.getText().equals("-")){
            solomenosenpantalla=true;
        }else {
            solomenosenpantalla=false;
        }
        if (botonIgualPress && tecla.matches("[1-9.]"))
        {
            txtPantalla.clear();
            operador="";
            resul=0;
            botonIgualPress=false;
        } else if (botonIgualPress && tecla.matches("[-+*/]")) {
            int numletras=0;
            for (int i = 0; i < txtPantalla.getText().length(); i++) {
                if(!Character.isDigit(txtPantalla.getText().charAt(i))){
                    numletras++;
                }
            }
            if (numletras==0){
                operador=tecla;
            } else if (numletras>0) {
                txtPantalla.clear();
                operador="";
                resul=0;
            }
        }

        if (tecla.matches("[1-9.]")) {
            txtPantalla.appendText(tecla);
        } else if (txtPantalla.getText().isEmpty() && tecla.matches("-")) {
            txtPantalla.appendText("-");
        } else if (txtPantalla.getText().equals("-") && tecla.matches("-")) {
            txtPantalla.clear();
        } else if (tecla.matches("[-+*/]") && !solomenosenpantalla) {
            if(operador.isEmpty()){
                operador=tecla;
                int puntos = txtPantalla.getText().length()-txtPantalla.getText().replace(".","").length();
                int contador=0;
                String datos=txtPantalla.getText();
                for (int i = 0; i <datos.length() ; i++) {
                    if (Character.isDigit(datos.charAt(i))){
                        contador++;
                    }
                }
                if ((contador ==0 && puntos >=1) || (contador >=1 && puntos >=2)) {
                    txtPantalla.clear();
                    txtPantalla.appendText("SINTAX ERROR: PUNTOS");
                    botonIgualPress=true;
                    return;
                }
                if (txtPantalla.getText().length()-txtPantalla.getText().replaceAll("-","").length() == 1){
                    String dato = txtPantalla.getText().replaceAll("-","");
                    n1 = Double.parseDouble(dato);
                    n1 = (-1)*n1;
                } else if (txtPantalla.getText().isEmpty()) {
                   n1=0;
                } else {
                    n1 = Double.parseDouble(txtPantalla.getText());
                }
                txtPantalla.clear();
            } else if (botonIgualPress && !operador.isEmpty()) {
                if (txtPantalla.getText().length()-txtPantalla.getText().replaceAll("-","").length() == 1){
                    String dato = txtPantalla.getText().replaceAll("-","");
                    n1 = Double.parseDouble(dato);
                    n1 = (-1)*n1;
                }else {
                    n1 = Double.parseDouble(txtPantalla.getText());
                }
                botonIgualPress=false;
                txtPantalla.clear();
            } else if (!operador.isEmpty()) {
                int puntos = txtPantalla.getText().length()-txtPantalla.getText().replace(".","").length();
                int contador=0;
                String datos=txtPantalla.getText();
                for (int i = 0; i <datos.length() ; i++) {
                    if (Character.isDigit(datos.charAt(i))){
                        contador++;
                    }
                }
                if ((contador ==0 && puntos >=1) || (contador >=1 && puntos >=2)) {
                    txtPantalla.clear();
                    txtPantalla.appendText("SINTAX ERROR: PUNTOS");
                    botonIgualPress=true;
                    return;
                }
                if (txtPantalla.getText().length()-txtPantalla.getText().replaceAll("-","").length() == 1){
                    String dato = txtPantalla.getText().replaceAll("-","");
                    n2 = Double.parseDouble(dato);
                    n2 = (-1)*n2;
                }else if (txtPantalla.getText().isEmpty()) {
                    n1=0;
                }else {
                    n2 = Double.parseDouble(txtPantalla.getText());
                }
                calcular();
                operador=tecla;
                n1=resul;
                txtPantalla.clear();
            }
        } else if (tecla.matches("=")&& !solomenosenpantalla) {
            if(txtPantalla.getText().isEmpty()){
                txtPantalla.appendText("0");
            }
            int puntos = txtPantalla.getText().length()-txtPantalla.getText().replace(".","").length();
            int contador=0;
            String datos=txtPantalla.getText();
            for (int i = 0; i <datos.length() ; i++) {
                if (Character.isDigit(datos.charAt(i))){
                    contador++;
                }
            }
            if ((contador ==0 && puntos >=1) || (contador >=1 && puntos >=2)) {
                txtPantalla.clear();
                txtPantalla.appendText("SINTAX ERROR: PUNTOS");
                botonIgualPress=true;
                return;
            }
            if (txtPantalla.getText().length()-txtPantalla.getText().replaceAll("-","").length() == 1){
                String dato = txtPantalla.getText().replaceAll("-","");
                n2 = Double.parseDouble(dato);
                n2 = (-1)*n2;
            }else {
                n2 = Double.parseDouble(txtPantalla.getText());
            }
            calcular();
            botonIgualPress=true;
        }
    }
    //METODO PARA ACCIONAR EL BOTON LIMPIAR
    private void clear(Button btnClear)
    {
        resul=0;
        operador="";
        txtPantalla.clear();
    }

    private void calcular(){
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
                    botonIgualPress=true;
                    break;
                }
                else
                {
                    resul = n1 / n2;
                    txtPantalla.setText(String.valueOf(resul));
                }
                break;
        }
    }




}