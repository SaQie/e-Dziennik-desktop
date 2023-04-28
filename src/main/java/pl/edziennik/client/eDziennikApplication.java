package pl.edziennik.client;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edziennik.client.common.factory.DialogFactory;
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.controller.auth.AuthorizationController;
import pl.edziennik.client.core.toast.Toast;
import pl.edziennik.client.core.toast.ToastType;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.exception.TableRowException;
import pl.edziennik.client.exception.TableViewException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class eDziennikApplication extends Application {

    private final AuthorizationController authorizationController;
    private final DialogFactory dialogFactory;
    private static final Logger LOGGER = Logger.getLogger(eDziennikApplication.class.getName());


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
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> {
            if (exception instanceof TableViewException) {
                dialogFactory.createErrorConfirmationDialog(null, exception.getMessage());
                exception.printStackTrace(printWriter);
                LOGGER.severe(stringWriter.toString());
                return;
            }
            if (exception instanceof TableRowException) {
                Toast.show(ToastType.ERROR, exception.getMessage());
                exception.printStackTrace(printWriter);
                LOGGER.severe(stringWriter.toString());
                return;
            }
            if (!(exception instanceof RestClientException)) {
                dialogFactory.createErrorConfirmationDialogFromRawStackTrace(exception.getStackTrace(), exception.getMessage());
                exception.printStackTrace(printWriter);
                LOGGER.severe(stringWriter.toString());
            }

        });
    }
}