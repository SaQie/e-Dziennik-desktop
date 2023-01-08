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
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.configuration.converter.PropertiesLanguageConverter;
import pl.edziennik.client.rest.client.response.ApiErrors;
import pl.edziennik.client.utils.AuthorizationUtils;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static pl.edziennik.client.common.ResourcesConstants.*;

public class DialogFactory {

    private final String alertStylesPath = getClass().getResource(ALERT_STYLES_ADDRESS).toExternalForm();
    private final ImageView informationIcon = new ImageView(getClass().getResource(INFORMATION_ICON_ADDRESS).toExternalForm());
    private final ImageView successIcon = new ImageView(getClass().getResource(SUCCESS_ICON_ADDRESS).toExternalForm());
    private final ImageView errorIcon = new ImageView(getClass().getResource(ERROR_ICON_ADDRESS).toExternalForm());

    private static DialogFactory factory;

    public static DialogFactory getInstance(){
        if (factory == null){
            factory = new DialogFactory();
        }
        return factory;
    }

    private final ButtonType YES_BUTTON = new ButtonType(ResourceUtil.getMessage(BUTTON_YES_KEY));
    private final ButtonType NO_BUTTON = new ButtonType(ResourceUtil.getMessage(BUTTON_NO_KEY));
    private final ButtonType OK_BUTTON = new ButtonType(ResourceUtil.getMessage(BUTTON_OK_KEY));


    public void createExitConfirmationDialog(Stage stage){
        Alert alert = createBasicInformationAlert();
        alert.initOwner(stage);
        alert.setTitle(ResourceUtil.getMessage(EXIT_CONFIRMATION_HEADER_KEY));
        alert.setHeaderText(ResourceUtil.getMessage(EXIT_CONFIRMATION_MESSAGE_KEY));
        alert.showAndWait();
        if (alert.getResult() == YES_BUTTON){
            stage.close();
        }
    }

    public void createErrorConfirmationDialog(String stackTrace, ApiErrors[] cause){
        Alert basicErrorAlert = createBasicErrorAlert();
        basicErrorAlert.setHeaderText(ResourceUtil.getMessage(ERROR_DIALOG_HEADER_MESSAGE_KEY));
        basicErrorAlert.setContentText(ResourceUtil.getMessage(ERROR_DIALOG_MESSAGE_KEY) + "\n" + Arrays.stream(cause).map(ApiErrors::getCause).collect(Collectors.joining("\n-", "-", "\n")));

        GridPane expContent = getStackTraceListView(stackTrace);

        basicErrorAlert.getDialogPane().setExpandableContent(expContent);

        basicErrorAlert.showAndWait();
    }


    public void createErrorConfirmationDialog(String stackTrace, String message){
        Alert basicErrorAlert = createBasicErrorAlert();
        basicErrorAlert.setHeaderText(ResourceUtil.getMessage(ERROR_DIALOG_HEADER_MESSAGE_KEY));
        basicErrorAlert.setContentText(ResourceUtil.getMessage(ERROR_DIALOG_MESSAGE_KEY) + "\n" + ResourceUtil.getMessage(message));

        if (stackTrace != null){
            GridPane expContent = getStackTraceListView(stackTrace);
            basicErrorAlert.getDialogPane().setExpandableContent(expContent);
        }

        basicErrorAlert.showAndWait();
    }

    public void createErrorConfirmationDialogFromRawStackTrace(StackTraceElement[] stackTrace, String message){
        Alert basicErrorAlert = createBasicErrorAlert();
        basicErrorAlert.setHeaderText(ResourceUtil.getMessage(ERROR_DIALOG_HEADER_MESSAGE_KEY));
        basicErrorAlert.setContentText(ResourceUtil.getMessage(ERROR_DIALOG_MESSAGE_KEY) + "\n" + ResourceUtil.getMessage(message));

        if (stackTrace != null){
            GridPane expContent = getStackTraceListView(Arrays.toString(stackTrace));
            basicErrorAlert.getDialogPane().setExpandableContent(expContent);
        }

        basicErrorAlert.showAndWait();
    }


    public void createLogoutConfirmationDialog(Stage stage) {
        Alert logoutConfirmationDialog = createBasicInformationAlert();
        logoutConfirmationDialog.setTitle(ResourceUtil.getMessage(LOGOUT_DIALOG_TITLE_MESSAGE_KEY));
        logoutConfirmationDialog.setHeaderText(ResourceUtil.getMessage(LOGOUT_DIALOG_HEADER_MESSAGE_KEY));
        logoutConfirmationDialog.showAndWait();
        if (logoutConfirmationDialog.getResult() == YES_BUTTON){
            stage.close();
            AuthorizationUtils.clearAuthorizationData();
            AuthorizationUtils.loadAuthorizationPage();
        }
    }

    public void createSuccessInformationDialog(String message){
        Alert basicSuccessAlert = createBasicSuccessAlert();
        basicSuccessAlert.setHeaderText(ResourceUtil.getMessage(SUCCESS_DIALOG_HEADER_MESSAGE_KEY));
        if (message != null) {
            basicSuccessAlert.setContentText(ResourceUtil.getMessage(message));
        }else{
            basicSuccessAlert.setContentText(ResourceUtil.getMessage(SUCCESS_DIALOG_CONTENT_MESSAGE_KEY));
        }
        basicSuccessAlert.showAndWait();
    }

    private Alert createBasicSuccessAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, null, OK_BUTTON);
        alert.initStyle(StageStyle.UTILITY);
        Stage stage = (Stage) Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
        if (stage != null){
            alert.initOwner(stage);
        }
        alert.getDialogPane().getStylesheets().add(alertStylesPath);
        alert.getDialogPane().setGraphic(successIcon);
        alert.setTitle(ResourceUtil.getMessage(SUCCESS_DIALOG_TITLE_MESSAGE_KEY));
        return alert;
    }

    private Alert createBasicInformationAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, null, NO_BUTTON,YES_BUTTON);
        alert.initStyle(StageStyle.UTILITY);
        Stage stage = (Stage) Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
        if (stage != null){
            alert.initOwner(stage);
        }
        alert.getDialogPane().getStylesheets().add(alertStylesPath);
        alert.getDialogPane().setGraphic(informationIcon);
        return alert;
    }

    private Alert createBasicErrorAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR, null, OK_BUTTON);
        Stage stage = (Stage) Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
        if (stage != null){
            alert.initOwner(stage);
        }
        alert.initStyle(StageStyle.UTILITY);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.getDialogPane().getStylesheets().add(alertStylesPath);
        alert.getDialogPane().setGraphic(errorIcon);
        return alert;
    }

    private static GridPane getStackTraceListView(String stackTrace) {
        Label label = new Label();

        TextArea textArea = new TextArea(stackTrace);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        return expContent;
    }

}
