package pl.edziennik.client.controller.auth;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.edziennik.client.eDziennikApplication;

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
        VARIABLES
     */

    private static final String AUTHORIZATION_VIEW_TITLE = "e-Dziennik";
    private static final int WIDTH = 650;
    private static final int HEIGHT = 450;

    /*
        CODE
     */

    public void loadAuthorizationView(Stage stage) throws IOException {
        Locale.setDefault(new Locale("en"));
        FXMLLoader fxmlLoader = new FXMLLoader(eDziennikApplication.class.getResource("authorization-view.fxml"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES_RESOURCES_ADDRESS);
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(AUTHORIZATION_VIEW_TITLE);
        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
