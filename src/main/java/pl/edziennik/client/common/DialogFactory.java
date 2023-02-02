package pl.edziennik.client.common;

import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.edziennik.client.common.builder.CommonStageBuilder;
import pl.edziennik.client.rest.client.response.ApiErrors;
import pl.edziennik.client.utils.AuthorizationUtils;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.Arrays;

import static pl.edziennik.client.common.ResourceConst.*;
import static pl.edziennik.client.common.builder.CommonStageBuilder.ALERT_STYLES_PATCH;
import static pl.edziennik.client.common.builder.CommonStageBuilder.YES_BUTTON;

public class DialogFactory {


    private static DialogFactory factory;

    boolean isShowing;

    public static DialogFactory getInstance() {
        if (factory == null) {
            factory = new DialogFactory();
        }
        return factory;
    }


    public void createExitConfirmationDialog(Stage stage) {
        if (!isShowing) {
            Alert alert = CommonStageBuilder.dialogBuilder()
                    .withOwner(stage)
                    .withTitle(EXIT_CONFIRMATION_HEADER_KEY.value())
                    .withHeaderText(EXIT_CONFIRMATION_MESSAGE_KEY.value())
                    .withAlertType(Alert.AlertType.CONFIRMATION)
                    .withCssStyles(ALERT_STYLES_PATCH)
                    .build();
            isShowing = true;
            alert.showAndWait();
            if (alert.getResult() == YES_BUTTON) {
                stage.close();
            }
            isShowing = false;
        }
    }

    public void createErrorConfirmationDialog(ApiErrors[] cause) {
        if (!isShowing) {
            Alert alert = CommonStageBuilder.dialogBuilder()
                    .withSearchActualStage()
                    .withTitle(EXIT_CONFIRMATION_HEADER_KEY.value())
                    .withAlertType(Alert.AlertType.ERROR)
                    .withErrors(cause)
                    .withCssStyles(ALERT_STYLES_PATCH)
                    .withStageStyle(StageStyle.UTILITY)
                    .withModality(Modality.APPLICATION_MODAL)
                    .build();
            isShowing = true;
            alert.showAndWait();
            isShowing = false;
        }
    }


    public void createErrorConfirmationDialog(String stackTrace, String message) {
        if (!isShowing) {
            Alert alert = CommonStageBuilder.dialogBuilder()
                    .withSearchActualStage()
                    .withTitle(ERROR_DIALOG_TITLE_MESSAGE_KEY.value())
                    .withHeaderText(ERROR_DIALOG_HEADER_MESSAGE_KEY.value())
                    .withAlertType(Alert.AlertType.ERROR)
                    .withContentText(ERROR_DIALOG_MESSAGE_KEY.value())
                    .withPlainCause(message)
                    .withStackTrace(stackTrace)
                    .withCssStyles(ALERT_STYLES_PATCH)
                    .withStageStyle(StageStyle.UTILITY)
                    .withModality(Modality.APPLICATION_MODAL)
                    .build();
            isShowing = true;
            alert.showAndWait();
            isShowing = false;
        }
    }

    public void createErrorProgressDialog(String stackTrace, String taskName) {
        if (!isShowing) {
            Alert alert = CommonStageBuilder.dialogBuilder()
                    .withTitle(EXIT_CONFIRMATION_HEADER_KEY.value())
                    .withHeaderText(ERROR_DIALOG_HEADER_MESSAGE_KEY.value())
                    .withAlertType(Alert.AlertType.ERROR)
                    .withPlainContentText(ResourceUtil.getMessage(TASK_ERROR_MESSAGE_KEY.value()) + taskName)
                    .withStackTrace(stackTrace)
                    .withCssStyles(ALERT_STYLES_PATCH)
                    .withStageStyle(StageStyle.UTILITY)
                    .withModality(Modality.APPLICATION_MODAL)
                    .build();
            isShowing = true;
            alert.showAndWait();
            isShowing = false;
        }
    }

    public void createErrorConfirmationDialogFromRawStackTrace(StackTraceElement[] stackTrace, String message) {
        if (!isShowing) {
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
            isShowing = true;
            alert.showAndWait();
            isShowing = false;
        }
    }


    public void createLogoutConfirmationDialog(Stage stage) {
        if (!isShowing) {
            Alert alert = CommonStageBuilder.dialogBuilder()
                    .withOwner(stage)
                    .withTitle(LOGOUT_DIALOG_TITLE_MESSAGE_KEY.value())
                    .withHeaderText(LOGOUT_DIALOG_HEADER_MESSAGE_KEY.value())
                    .withAlertType(Alert.AlertType.CONFIRMATION)
                    .withCssStyles(ALERT_STYLES_PATCH)
                    .build();
            isShowing = true;
            alert.showAndWait();
            if (alert.getResult() == YES_BUTTON) {
                stage.close();
                AuthorizationUtils.clearAuthorizationData();
                AuthorizationUtils.loadAuthorizationPage();
            }
            isShowing = false;
        }
    }

    public void createSuccessInformationDialog(String message) {
        if (!isShowing) {
            Alert alert = CommonStageBuilder.dialogBuilder()
                    .withSearchActualStage()
                    .withTitle(SUCCESS_DIALOG_TITLE_MESSAGE_KEY.value())
                    .withHeaderText(SUCCESS_DIALOG_HEADER_MESSAGE_KEY.value())
                    .withAlertType(Alert.AlertType.INFORMATION)
                    .withCssStyles(ALERT_STYLES_PATCH)
                    .withContentText(message)
                    .withDefaultContentText()
                    .build();
            isShowing = true;
            alert.showAndWait();
            isShowing = false;
        }
    }

    public boolean createQuestionInformationDialog(String message){
        if (!isShowing) {
            Alert informationDialog = CommonStageBuilder.dialogBuilder()
                    .withSearchActualStage()
                    .withTitle(DIALOG_QUESTION_MESSAGE_KEY.value())
                    .withHeaderText(ARE_YOU_SURE_TO_PERFORM_THIS_OPERATION_MESSAGE_KEY.value())
                    .withAlertType(Alert.AlertType.CONFIRMATION)
                    .withCssStyles(ALERT_STYLES_PATCH)
                    .withContentText(message)
                    .withModality(Modality.APPLICATION_MODAL)
                    .withStageStyle(StageStyle.UTILITY)
                    .build();
            isShowing = true;
            informationDialog.showAndWait();
            isShowing = false;
            return informationDialog.getResult() == YES_BUTTON;
        }
        return false;
    }

}
