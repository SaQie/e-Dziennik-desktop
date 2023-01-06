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

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static pl.edziennik.client.common.ResourcesConstants.*;

public class DialogFactory {

    private final String alertStylesPath = getClass().getResource(ALERT_STYLES_ADDRESS).toExternalForm();
    private final ImageView informationIcon = new ImageView(getClass().getResource(INFORMATION_ICON_ADDRESS).toExternalForm());
    private final ImageView successIcon = new ImageView(getClass().getResource(SUCCESS_ICON_ADDRESS).toExternalForm());
    private final ImageView errorIcon = new ImageView(getClass().getResource(ERROR_ICON_ADDRESS).toExternalForm());
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES_RESOURCES_ADDRESS, PropertiesLoader.readProperty("language", new PropertiesLanguageConverter()));

    private static DialogFactory factory;

    public static DialogFactory getInstance(){
        if (factory == null){
            factory = new DialogFactory();
        }
        return factory;
    }

    private final String EXIT_CONFIRMATION_DIALOG_MESSAGE = resourceBundle.getString(EXIT_CONFIRMATION_MESSAGE_KEY);
    private final String EXIT_CONFIRMATION_DIALOG_HEADER_MESSAGE = resourceBundle.getString(EXIT_CONFIRMATION_HEADER_KEY);
    private final String ERROR_DIALOG_MESSAGE = resourceBundle.getString(ERROR_DIALOG_MESSAGE_KEY);
    private final String ERROR_DIALOG_HEADER_MESSAGE = resourceBundle.getString(ERROR_DIALOG_HEADER_MESSAGE_KEY);
    private final String SUCCESS_DIALOG_HEADER_MESSAGE = resourceBundle.getString(SUCCESS_DIALOG_HEADER_MESSAGE_KEY);
    private final String BASIC_SUCCESS_DIALOG_MESSAGE = resourceBundle.getString(SUCCESS_DIALOG_CONTENT_MESSAGE_KEY);
    private final String SUCCESS_DIALOG_TITLE = resourceBundle.getString(SUCCESS_DIALOG_TITLE_MESSAGE_KEY);
    private final ButtonType YES_BUTTON = new ButtonType(resourceBundle.getString(BUTTON_YES_KEY));
    private final ButtonType NO_BUTTON = new ButtonType(resourceBundle.getString(BUTTON_NO_KEY));
    private final ButtonType OK_BUTTON = new ButtonType(resourceBundle.getString(BUTTON_OK_KEY));
    private final String LOGOUT_CONFIRMATION_DIALOG_TITLE_MESSAGE = resourceBundle.getString(LOGOUT_DIALOG_TITLE_MESSAGE_KEY);
    private final String LOGOUT_CONFIRMATION_DIALOG_HEADER_MESSAGE = resourceBundle.getString(LOGOUT_DIALOG_HEADER_MESSAGE_KEY);


    public void createExitConfirmationDialog(Stage stage){
        Alert alert = createBasicInformationAlert();
        alert.initOwner(stage);
        alert.setTitle(EXIT_CONFIRMATION_DIALOG_HEADER_MESSAGE);
        alert.setHeaderText(EXIT_CONFIRMATION_DIALOG_MESSAGE);
        alert.showAndWait();
        if (alert.getResult() == YES_BUTTON){
            stage.close();
        }
    }

    public void createErrorConfirmationDialog(String stackTrace, ApiErrors[] cause){
        Alert basicErrorAlert = createBasicErrorAlert();
        basicErrorAlert.setHeaderText(ERROR_DIALOG_HEADER_MESSAGE);
        basicErrorAlert.setContentText(ERROR_DIALOG_MESSAGE + "\n" + Arrays.stream(cause).map(ApiErrors::getCause).collect(Collectors.joining("-", "-", "\n")));

        GridPane expContent = getStackTraceListView(stackTrace);

        basicErrorAlert.getDialogPane().setExpandableContent(expContent);

        basicErrorAlert.showAndWait();
    }


    public void createErrorConfirmationDialog(String stackTrace, String message){
        Alert basicErrorAlert = createBasicErrorAlert();
        basicErrorAlert.setHeaderText(ERROR_DIALOG_HEADER_MESSAGE);
        basicErrorAlert.setContentText(ERROR_DIALOG_MESSAGE + "\n" + resourceBundle.getString(message));

        if (stackTrace != null){
            GridPane expContent = getStackTraceListView(stackTrace);
            basicErrorAlert.getDialogPane().setExpandableContent(expContent);
        }

        basicErrorAlert.showAndWait();
    }

    public void createErrorConfirmationDialogFromRawStackTrace(StackTraceElement[] stackTrace, String message){
        Alert basicErrorAlert = createBasicErrorAlert();
        basicErrorAlert.setHeaderText(ERROR_DIALOG_HEADER_MESSAGE);
        basicErrorAlert.setContentText(ERROR_DIALOG_MESSAGE + "\n" + resourceBundle.getString(message));

        if (stackTrace != null){
            GridPane expContent = getStackTraceListView(Arrays.toString(stackTrace));
            basicErrorAlert.getDialogPane().setExpandableContent(expContent);
        }

        basicErrorAlert.showAndWait();
    }


    public void createLogoutConfirmationDialog(Stage stage) {
        Alert logoutConfirmationDialog = createBasicInformationAlert();
        logoutConfirmationDialog.setTitle(LOGOUT_CONFIRMATION_DIALOG_TITLE_MESSAGE);
        logoutConfirmationDialog.setHeaderText(LOGOUT_CONFIRMATION_DIALOG_HEADER_MESSAGE);
        logoutConfirmationDialog.showAndWait();
        if (logoutConfirmationDialog.getResult() == YES_BUTTON){
            stage.close();
            AuthorizationUtils.clearAuthorizationData();
            AuthorizationUtils.loadAuthorizationPage();
        }
    }

    public void createSuccessInformationDialog(String message){
        Alert basicSuccessAlert = createBasicSuccessAlert();
        basicSuccessAlert.setHeaderText(SUCCESS_DIALOG_HEADER_MESSAGE);
        if (message != null) {
            basicSuccessAlert.setContentText(resourceBundle.getString(message));
        }else{
            basicSuccessAlert.setContentText(BASIC_SUCCESS_DIALOG_MESSAGE);
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
        alert.setTitle(SUCCESS_DIALOG_TITLE);
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
