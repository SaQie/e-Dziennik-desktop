module pl.edziennik.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens pl.edziennik.client to javafx.fxml;
    exports pl.edziennik.client;
}