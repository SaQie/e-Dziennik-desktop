package pl.edziennik.client.controller.auth;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.edziennik.client.eDziennikApplication;
import pl.edziennik.client.utils.AuthorizationUtils;
import pl.edziennik.client.utils.NodeUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import static pl.edziennik.client.common.ResourcesConstants.*;

public class AuthorizationController implements Initializable {

    /*
        FXML
     */

    @FXML
    private AnchorPane authorizationViewPane;

    /*
        CODE
     */

    public void loadAuthorizationView(Stage stage) throws IOException {
        AuthorizationUtils.loadAuthorizationPage();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
