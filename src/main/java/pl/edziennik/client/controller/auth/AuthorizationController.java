package pl.edziennik.client.controller.auth;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.edziennik.client.utils.AuthorizationUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthorizationController implements Initializable {

    public void loadAuthorizationView(Stage stage) throws IOException {
        AuthorizationUtils.loadAuthorizationPage();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
