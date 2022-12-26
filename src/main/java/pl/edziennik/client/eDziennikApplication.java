package pl.edziennik.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import pl.edziennik.client.common.ConfirmationDialogFactory;
import pl.edziennik.client.controller.auth.AuthorizationController;

import java.io.IOException;

public class eDziennikApplication extends Application {

    private final AuthorizationController authorizationController;
    private final ConfirmationDialogFactory dialogFactory = ConfirmationDialogFactory.getInstance();

    public eDziennikApplication() {
        this.authorizationController = new AuthorizationController();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> Platform.runLater(() -> showErrorDialog(t, e)));
        Thread.currentThread().setUncaughtExceptionHandler(this::showErrorDialog);
        authorizationController.loadAuthorizationView(stage);
    }

    private void showErrorDialog(Thread t, Throwable e) {
        dialogFactory.createErrorConfirmationDialog(e.getMessage(),"dupa");
//        Platform.exit();
    }

    public static void main(String[] args) {
        launch();
    }
}