module com.example.slfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.slfx to javafx.fxml;
    exports com.example.slfx;
}