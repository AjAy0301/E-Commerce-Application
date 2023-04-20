module com.example.ecommerceapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.ecommerceapplication to javafx.fxml;
    exports com.example.ecommerceapplication;
}