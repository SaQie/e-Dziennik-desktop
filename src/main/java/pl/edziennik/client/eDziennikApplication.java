package pl.edziennik.client;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edziennik.client.controller.auth.AuthorizationController;

import java.io.IOException;

public class eDziennikApplication extends Application {

    private final AuthorizationController authorizationController;

    public eDziennikApplication() {
        this.authorizationController = new AuthorizationController();
    }

    @Override
    public void start(Stage stage) throws IOException {
        authorizationController.loadAuthorizationView(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}