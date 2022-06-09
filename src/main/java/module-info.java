module at.spengergasse.graphenprogramm {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.spengergasse.graphenprogramm to javafx.fxml;
    exports at.spengergasse.graphenprogramm;
    exports at.spengergasse.graphenprogramm.model;
    opens at.spengergasse.graphenprogramm.model to javafx.fxml;
}