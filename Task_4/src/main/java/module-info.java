module com.example.cw_task4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens javaFxTable to javafx.fxml;
    exports javaFxTable;
}