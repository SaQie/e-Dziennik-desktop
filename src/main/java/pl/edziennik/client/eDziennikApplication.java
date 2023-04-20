package pl.edziennik.client;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.web.client.ResourceAccessException;
import pl.edziennik.client.common.factory.DialogFactory;
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.controller.auth.AuthorizationController;
import pl.edziennik.client.core.toast.Toast;
import pl.edziennik.client.core.toast.ToastType;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.exception.TableRowException;
import pl.edziennik.client.exception.TableViewException;
import pl.edziennik.client.utils.NodeUtils;

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
            if (exception instanceof TableRowException) {
                Toast.show(ToastType.ERROR, exception.getMessage());
                return;
            }
            if (!(exception instanceof RestClientException)) {
                dialogFactory.createErrorConfirmationDialogFromRawStackTrace(exception.getStackTrace(), exception.getMessage());
            }

        });
    }
}