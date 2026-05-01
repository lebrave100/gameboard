module org.example.progame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.example.progame to javafx.fxml;
    exports org.example.progame;
}