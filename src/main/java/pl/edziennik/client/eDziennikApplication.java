package pl.edziennik.client;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edziennik.client.common.DialogFactory;
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.controller.auth.AuthorizationController;
import pl.edziennik.client.exception.BusinessException;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.exception.TableViewException;
import pl.edziennik.client.exception.ViewException;

import java.io.IOException;

public class eDziennikApplication extends Application {

    private final AuthorizationController authorizationController;
    private final DialogFactory dialogFactory;

    public eDziennikApplication() {
        this.authorizationController = new AuthorizationController();
        this.dialogFactory = DialogFactory.getInstance();
    }

    @Override
    public void start(Stage stage) throws IOException {
        PropertiesLoader.loadDataFromFile();
        setExceptionHandler();
        authorizationController.loadAuthorizationView(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    private void setExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> {
            if (exception instanceof TableViewException) {
                dialogFactory.createErrorConfirmationDialog(null, exception.getMessage());
                return;
            }
            if (!(exception instanceof RestClientException)) {
                dialogFactory.createErrorConfirmationDialogFromRawStackTrace(exception.getStackTrace(), exception.getMessage());
            }
        });
    }
}