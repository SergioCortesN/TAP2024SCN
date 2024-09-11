module com.example.tap2024scn {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.tap2024scn to javafx.fxml;
    exports com.example.tap2024scn;
    requires org.kordamp.bootstrapfx.core;
}