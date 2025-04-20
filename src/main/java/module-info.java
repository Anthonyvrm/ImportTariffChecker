module com.example.project_importtariffchecker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires java.sql;


    opens com.example.project_importtariffchecker to javafx.fxml;
    exports com.example.project_importtariffchecker;
    exports Controller;
    opens Controller to javafx.fxml;

    opens Classes to javafx.base, javafx.fxml;

}
