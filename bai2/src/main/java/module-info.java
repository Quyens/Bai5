module com.vanquyenit.socketb2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.vanquyenit.socketb2 to javafx.fxml;
    exports com.vanquyenit.socketb2;
}