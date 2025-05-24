module ClinicManagementSystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens ClinicManagement to javafx.fxml;
    exports ClinicManagement;
}