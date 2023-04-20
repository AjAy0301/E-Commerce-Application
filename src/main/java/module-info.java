module com.example.ecommerceapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ecommerceapplication to javafx.fxml;
    exports com.example.ecommerceapplication;
}