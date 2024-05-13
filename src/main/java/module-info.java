module org.proj2.oopprojekt2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.proj2.oopprojekt2 to javafx.fxml;
    exports org.proj2.oopprojekt2;
}