package pl.edziennik.client.common;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import pl.edziennik.client.rest.ApiErrors;

import static pl.edziennik.client.common.ResourcesConstants.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ConfirmationDialogFactory {

    private final String alertStylesPath = getClass().getResource(ALERT_STYLES_ADDRESS).toExternalForm();
    private final ImageView informationIcon = new ImageView(getClass().getResource(INFORMATION_ICON_ADDRESS).toExternalForm());
    private final ImageView errorIcon = new ImageView(getClass().getResource(ERROR_ICON_ADDRESS).toExternalForm());
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES_RESOURCES_ADDRESS);

    private static ConfirmationDialogFactory factory;

    public static ConfirmationDialogFactory getInstance(){
        if (factory == null){
            factory = new ConfirmationDialogFactory();
        }
        return factory;
    }

    private final String EXIT_CONFIRMATION_DIALOG_MESSAGE = resourceBundle.getString(EXIT_CONFIRMATION_MESSAGE_KEY);
    private final String EXIT_CONFIRMATION_DIALOG_HEADER_MESSAGE = resourceBundle.getString(EXIT_CONFIRMATION_HEADER_KEY);
    private final String ERROR_DIALOG_MESSAGE = resourceBundle.getString(ERROR_DIALOG_MESSAGE_KEY);
    private final String ERROR_DIALOG_HEADER_MESSAGE = resourceBundle.getString(ERROR_DIALOG_HEADER_MESSAGE_KEY);
    private final ButtonType YES_BUTTON = new ButtonType(resourceBundle.getString(BUTTON_YES_KEY));
    private final ButtonType NO_BUTTON = new ButtonType(resourceBundle.getString(BUTTON_NO_KEY));
    private final ButtonType OK_BUTTON = new ButtonType(resourceBundle.getString(BUTTON_OK_KEY));


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
