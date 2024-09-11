package vistas;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.sql.Time;

public class Loteria extends Stage
{
    private HBox hbMain,hbButtons;
    private VBox vbTablilla, vbMazo;
    private Button btnAnt, btnSig, btnInit;
    private Label lbTimer;
    private int tiempo;
    private GridPane gdpTablilla;
    private ImageView imvMazo;
    private Scene escena;
    private String[] arImages = {"barril.jpeg","botella.jpeg","catrin.jpeg","chavorruco.jpeg","concha.jpeg","dorso.jpeg","luchador.jpeg","maceta.jpeg","rosa.jpeg","tacos.jpeg","venado.jpeg"};
    private Button[][] arbtTab;

    private Panel pnlPrincipal;

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

        gdpTablilla = new GridPane();
        CrearTablilla();

        btnAnt = new Button();
        btnAnt.setGraphic(imvAnt);
        btnSig = new Button();
        btnSig.setGraphic(imvSig);
        hbButtons = new HBox(btnAnt, btnSig);

        crearMazo();

        vbTablilla = new VBox(gdpTablilla,hbButtons);
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
        int l=0;
        arbtTab=new Button[4][4];
        Image img;
        ImageView imv;
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                img=new Image(getClass().getResource("/images loteria/"+arImages[l]).toExternalForm());
                imv=new ImageView(img);
                arbtTab[j][i]=new Button();
                arbtTab[j][i].setGraphic(imv);
                imv.setFitHeight(150);
                imv.setFitWidth(100);
                gdpTablilla.add(arbtTab[j][i],j,i);
                if (l<arImages.length-1)
                {
                    l++;
                }
            }
        }
    }

    private void crearMazo()
    {
        Image imgMazo = new Image(getClass().getResource("/images loteria/dorso.jpeg").toExternalForm());
        lbTimer = new Label("00:00");
        imvMazo = new ImageView(imgMazo);
        imvMazo.setFitHeight(500);
        imvMazo.setFitWidth(350);
        btnInit = new Button("INICIAR");
        btnInit.getStyleClass().setAll("btn-lg","btn-success");
        vbMazo = new VBox(lbTimer, imvMazo, btnInit);
        vbMazo.setSpacing(10);
    }

}
