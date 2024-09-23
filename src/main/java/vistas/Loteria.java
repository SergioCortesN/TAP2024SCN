package vistas;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;


public class Loteria extends Stage
{
    private HBox hbMain,hbButtons;
    private VBox vbTablilla, vbMazo;
    private Button btnAnt, btnSig, btnInit;
    private Label lbTimer;
    private GridPane []gdpTablilla;
    private ImageView imvMazo;
    private Scene escena;
    private String[] arImages = {"1.jpeg", "2.jpeg", "3.jpeg", "4.jpeg", "5.jpeg", "6.jpeg", "7.jpeg", "8.jpeg", "9.jpeg", "10.jpeg", "11.jpeg", "12.jpeg", "13.jpeg", "14.jpeg", "15.jpeg", "16.jpeg", "17.jpeg", "18.jpeg", "19.jpeg", "20.jpeg", "21.jpeg", "22.jpeg", "23.jpeg", "24.jpeg", "25.jpeg", "26.jpeg", "27.jpeg", "28.jpeg", "29.jpeg", "30.jpeg", "31.jpeg", "32.jpeg", "33.jpeg", "34.jpeg", "35.jpeg", "36.jpeg", "37.jpeg", "38.jpeg", "39.jpeg", "40.jpeg", "41.jpeg", "42.jpeg", "43.jpeg", "44.jpeg", "45.jpeg", "46.jpeg", "47.jpeg", "48.jpeg", "49.jpeg", "50.jpeg", "51.jpeg", "52.jpeg", "53.jpeg", "54.jpeg"};
    private Button[][] arbtTab;
    private Panel pnlPrincipal;
    private boolean botonInPress=false;
    private int contador=0;
    private int segundos;
    private int contador2=0;
    private int pasar=0;
    private Timeline temp;
    private int []mazo;
    public Loteria()
    {
        CrearUI();
        this.setTitle("Loteria Mexicana (._.')");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI()
    {

        ImageView imvAnt,imvSig;
        imvAnt = new ImageView(new Image(getClass().getResource("/images loteria/last.png").toExternalForm()));
        imvAnt.setFitHeight(50);
        imvAnt.setFitWidth(50);
        imvSig = new ImageView(new Image(getClass().getResource("/images loteria/next.png").toExternalForm()));
        imvSig.setFitHeight(50);
        imvSig.setFitWidth(50);

        gdpTablilla = new GridPane[5];
        btnAnt = new Button();
        btnAnt.setGraphic(imvAnt);
        btnAnt.setOnAction(event -> anterior());
        btnSig = new Button();
        btnSig.setGraphic(imvSig);
        btnSig.setOnAction(event -> siguiente());
        hbButtons = new HBox(btnAnt, btnSig);
        CrearTablilla();

        crearMazo();
        vbTablilla = new VBox(gdpTablilla[contador],hbButtons);
        hbMain = new HBox(vbTablilla,vbMazo);
        pnlPrincipal = new Panel("LOTERIA MEXICANA :3");
        pnlPrincipal.getStyleClass().add("panel-success");
        pnlPrincipal.setBody(hbMain);
        hbMain.setSpacing(20);
        hbMain.setPadding(new Insets(20));
        escena=new Scene(pnlPrincipal,900,720);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        escena.getStylesheets().add(getClass().getResource("/styles/Loteria.css").toExternalForm());

    }

    private void CrearTablilla()
    {

        arbtTab=new Button[4][4];
        Image img;
        ImageView imv;
        for (int k = 0; k < 5; k++)
        {
            int []cartas=numeroRandom();
            int l=0;
            gdpTablilla[k] = new GridPane();
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    img=new Image(getClass().getResource("/images loteria/"+arImages[cartas[l]]).toExternalForm());
                    imv=new ImageView(img);
                    arbtTab[j][i]=new Button();
                    arbtTab[j][i].setGraphic(imv);
                    imv.setFitHeight(150);
                    imv.setFitWidth(100);
                    int in=j;
                    int fin=i;
                    gdpTablilla[k].add(arbtTab[j][i],j,i);
                    int tablilla=k;
                    int posicionBoton=l;
                    gdpTablilla[k].getChildren().get(l).setOnMouseClicked(event -> cambio((Button) gdpTablilla[tablilla].getChildren().get(posicionBoton)));
                    if(l<16){
                        l++;
                    }
                }
            }
        }
    }

    private void cambio(Button carta) {
        if (botonInPress) {
            carta.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        }

        ImageView t= (ImageView) carta.getGraphic();
        Image p=t.getImage();
        Image k= new Image(getClass().getResource("/images loteria/"+arImages[mazo[contador2]]).toExternalForm());
        PixelReader img1=p.getPixelReader();
        PixelReader img2=k.getPixelReader();
        Color color = img1.getColor(0, 0);
        Color color2 = img2.getColor(0, 0);
        if(color.equals(color2)){
            System.out.println("iguales");
        } else if (!color.equals(color2)) {
            System.out.println("no iguales");
        }


    }

    private void crearMazo()
    {
        Image imgMazo = new Image(getClass().getResource("/images loteria/dorso.jpeg").toExternalForm());
        lbTimer = new Label("00:05");
        lbTimer.setFont(new Font("Arial", 50));
        imvMazo = new ImageView(imgMazo);
        imvMazo.setFitHeight(400);
        imvMazo.setFitWidth(250);
        btnInit = new Button("INICIAR");
        btnInit.getStyleClass().setAll("btn","btn-success");
        btnInit.setOnAction(event -> iniciar());
        vbMazo = new VBox(lbTimer, imvMazo, btnInit);
        vbMazo.setSpacing(10);
    }


    private void iniciar()
    {
        botonInPress=true;
        mazoRandom();
        tiempo();
    }

    private void anterior(){
        if(!botonInPress){
            if(contador==0){
                contador=4;
            }else if (contador>0){
                contador--;
            }
            vbTablilla.getChildren().set(0,gdpTablilla[contador]);
        }

    }

    private void siguiente(){
        if(!botonInPress){
            if(contador==4){
                contador=0;
            }else if (contador<4){
                contador++;
            }
            vbTablilla.getChildren().set(0,gdpTablilla[contador]);
        }

    }

    private int[] numeroRandom() {
        int[] cartas = new int[16];
        Set<Integer> repetidos = new HashSet<>();

        for (int i = 0; i < 16; i++) {
            int aux;
            do {
                aux = (int) (Math.random() * 54);
            } while (repetidos.contains(aux));
            repetidos.add(aux);
            cartas[i] = aux;
        }

        return cartas;
    }

    private void tiempo(){
        segundos=5;
        temp = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if(segundos>0){
               lbTimer.setText("00:0"+segundos);
                ImageView carta = new ImageView(getClass().getResource("/images loteria/"+arImages[mazo[contador2]]).toExternalForm());
                carta.setFitHeight(400);
                carta.setFitWidth(250);
                vbMazo.getChildren().set(1,carta);
               segundos--;
            } else if (segundos==0) {
               lbTimer.setText("00:05");
               contador2++;
               if(pasar<arImages.length){
                   temp.stop();
                   tiempo();
                   pasar++;
               }
            }
        }));
        temp.setCycleCount(Timeline.INDEFINITE);
        temp.play();
    }

    private void mazoRandom(){
        mazo = new int[arImages.length];
        Set<Integer> repes = new HashSet<>();

        for (int i = 0; i < mazo.length; i++) {
            int aux;
            do {
                aux = (int) (Math.random() * 54);
            } while (repes.contains(aux));
            repes.add(aux);
            mazo[i] = aux;
        }
    }
}