package pl.edziennik.client.common;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import pl.edziennik.client.common.builder.CommonStageBuilder;
import pl.edziennik.client.rest.client.response.ApiErrors;
import pl.edziennik.client.utils.AuthorizationUtils;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

import static pl.edziennik.client.common.ResourceConst.*;
import static pl.edziennik.client.common.builder.CommonStageBuilder.*;

public class DialogFactory {


    private static DialogFactory factory;

    public static DialogFactory getInstance() {
        if (factory == null) {
            factory = new DialogFactory();
        }
        return factory;
    }


    public void createExitConfirmationDialog(Stage stage) {
        Alert alert = CommonStageBuilder.dialogBuilder()
                .withOwner(stage)
                .withTitle(EXIT_CONFIRMATION_HEADER_KEY.value())
                .withHeaderText(EXIT_CONFIRMATION_MESSAGE_KEY.value())
                .withAlertType(Alert.AlertType.CONFIRMATION)
                .withCssStyles(ALERT_STYLES_PATCH)
                .build();
        alert.showAndWait();
        if (alert.getResult() == YES_BUTTON) {
            stage.close();
        }
    }

    public void createErrorConfirmationDialog(String stackTrace, ApiErrors[] cause) {
        Alert alert = CommonStageBuilder.dialogBuilder()
                .withSearchActualStage()
                .withTitle(EXIT_CONFIRMATION_HEADER_KEY.value())
                .withAlertType(Alert.AlertType.ERROR)
                .withErrors(cause)
                .withStackTrace(stackTrace)
                .withCssStyles(ALERT_STYLES_PATCH)
                .withStageStyle(StageStyle.UTILITY)
                .withModality(Modality.APPLICATION_MODAL)
                .build();
        alert.showAndWait();
    }


    public void createErrorConfirmationDialog(String stackTrace, String message) {
        Alert alert = CommonStageBuilder.dialogBuilder()
                .withSearchActualStage()
                .withTitle(EXIT_CONFIRMATION_HEADER_KEY.value())
                .withHeaderText(ERROR_DIALOG_HEADER_MESSAGE_KEY.value())
                .withAlertType(Alert.AlertType.ERROR)
                .withContentText(ERROR_DIALOG_MESSAGE_KEY.value())
                .withPlainCause(message)
                .withStackTrace(stackTrace)
                .withCssStyles(ALERT_STYLES_PATCH)
                .withStageStyle(StageStyle.UTILITY)
                .withModality(Modality.APPLICATION_MODAL)
                .build();
        alert.showAndWait();
    }

    public void createErrorConfirmationDialogFromRawStackTrace(StackTraceElement[] stackTrace, String message) {
        Alert alert = CommonStageBuilder.dialogBuilder()
                .withSearchActualStage()
                .withTitle(EXIT_CONFIRMATION_HEADER_KEY.value())
                .withHeaderText(ERROR_DIALOG_HEADER_MESSAGE_KEY.value())
                .withAlertType(Alert.AlertType.ERROR)
                .withContentText(ERROR_DIALOG_MESSAGE_KEY.value())
                .withPlainCause(message)
                .withStackTrace(Arrays.toString(stackTrace))
                .withCssStyles(ALERT_STYLES_PATCH)
                .withStageStyle(StageStyle.UTILITY)
                .withModality(Modality.APPLICATION_MODAL)
                .build();
        alert.showAndWait();
    }


    public void createLogoutConfirmationDialog(Stage stage) {
        Alert alert = CommonStageBuilder.dialogBuilder()
                .withOwner(stage)
                .withTitle(LOGOUT_DIALOG_TITLE_MESSAGE_KEY.value())
                .withHeaderText(LOGOUT_DIALOG_HEADER_MESSAGE_KEY.value())
                .withAlertType(Alert.AlertType.CONFIRMATION)
                .withCssStyles(ALERT_STYLES_PATCH)
                .build();
        alert.showAndWait();
        if (alert.getResult() == YES_BUTTON) {
            stage.close();
            AuthorizationUtils.clearAuthorizationData();
            AuthorizationUtils.loadAuthorizationPage();
        }
    }

    public void createSuccessInformationDialog(String message) {
        Alert alert = CommonStageBuilder.dialogBuilder()
                .withSearchActualStage()
                .withTitle(SUCCESS_DIALOG_TITLE_MESSAGE_KEY.value())
                .withHeaderText(SUCCESS_DIALOG_HEADER_MESSAGE_KEY.value())
                .withAlertType(Alert.AlertType.INFORMATION)
                .withCssStyles(ALERT_STYLES_PATCH)
                .withContentText(message)
                .withDefaultContentText()
                .build();
        alert.showAndWait();
    }

}
