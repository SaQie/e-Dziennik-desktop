module pl.edziennik.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires lombok;

    requires org.kordamp.ikonli.javafx;

    opens pl.edziennik.client to javafx.fxml;
    exports pl.edziennik.client;
    exports pl.edziennik.client.controller.auth;
    opens pl.edziennik.client.controller.auth to javafx.fxml;

}