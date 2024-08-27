module com.example.tap2024scn {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tap2024scn to javafx.fxml;
    exports com.example.tap2024scn;
}