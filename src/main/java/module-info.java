module com.example.tap2024scn.models {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.tap2024scn to javafx.fxml;
    exports com.example.tap2024scn;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.j;
    requires java.desktop;
}