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
import pl.edziennik.client.rest.client.response.ApiErrors;
import pl.edziennik.client.utils.AuthorizationUtils;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DialogFactory {

    private final String alertStylesPath = getClass().getResource(ResourceConst.ALERT_STYLES_ADDRESS.value()).toExternalForm();
    private final ImageView informationIcon = new ImageView(getClass().getResource(ResourceConst.INFORMATION_ICON_ADDRESS.value()).toExternalForm());
    private final ImageView successIcon = new ImageView(getClass().getResource(ResourceConst.SUCCESS_ICON_ADDRESS.value()).toExternalForm());
    private final ImageView errorIcon = new ImageView(getClass().getResource(ResourceConst.ERROR_ICON_ADDRESS.value()).toExternalForm());


    // Buttons

    private final ButtonType YES_BUTTON = new ButtonType(ResourceUtil.getMessage(ResourceConst.BUTTON_YES_KEY.value()));
    private final ButtonType NO_BUTTON = new ButtonType(ResourceUtil.getMessage(ResourceConst.BUTTON_NO_KEY.value()));
    private final ButtonType OK_BUTTON = new ButtonType(ResourceUtil.getMessage(ResourceConst.BUTTON_OK_KEY.value()));

    private static DialogFactory factory;

    public static DialogFactory getInstance(){
        if (factory == null){
            factory = new DialogFactory();
        }
        return factory;
    }


    public void createExitConfirmationDialog(Stage stage){
        Alert alert = createBasicInformationAlert();
        alert.initOwner(stage);
        alert.setTitle(ResourceUtil.getMessage(ResourceConst.EXIT_CONFIRMATION_HEADER_KEY.value()));
        alert.setHeaderText(ResourceUtil.getMessage(ResourceConst.EXIT_CONFIRMATION_MESSAGE_KEY.value()));
        alert.showAndWait();
        if (alert.getResult() == YES_BUTTON){
            stage.close();
        }
    }

    public void createErrorConfirmationDialog(String stackTrace, ApiErrors[] cause){
        Alert basicErrorAlert = createBasicErrorAlert();
        basicErrorAlert.setHeaderText(ResourceUtil.getMessage(ResourceConst.ERROR_DIALOG_HEADER_MESSAGE_KEY.value()));
        basicErrorAlert.setContentText(ResourceUtil.getMessage(ResourceConst.ERROR_DIALOG_MESSAGE_KEY.value()) + "\n" + Arrays.stream(cause).map(ApiErrors::getCause).collect(Collectors.joining("\n-", "-", "\n")));

        GridPane expContent = getStackTraceListView(stackTrace);

        basicErrorAlert.getDialogPane().setExpandableContent(expContent);

        basicErrorAlert.showAndWait();
    }


    public void createErrorConfirmationDialog(String stackTrace, String message){
        Alert basicErrorAlert = createBasicErrorAlert();
        basicErrorAlert.setHeaderText(ResourceUtil.getMessage(ResourceConst.ERROR_DIALOG_HEADER_MESSAGE_KEY.value()));
        basicErrorAlert.setContentText(ResourceUtil.getMessage(ResourceConst.ERROR_DIALOG_MESSAGE_KEY.value()) + "\n" + ResourceUtil.getMessage(message));

        if (stackTrace != null){
            GridPane expContent = getStackTraceListView(stackTrace);
            basicErrorAlert.getDialogPane().setExpandableContent(expContent);
        }

        basicErrorAlert.showAndWait();
    }

    public void createErrorConfirmationDialogFromRawStackTrace(StackTraceElement[] stackTrace, String message){
        Alert basicErrorAlert = createBasicErrorAlert();
        basicErrorAlert.setHeaderText(ResourceUtil.getMessage(ResourceConst.ERROR_DIALOG_HEADER_MESSAGE_KEY.value()));
        basicErrorAlert.setContentText(ResourceUtil.getMessage(ResourceConst.ERROR_DIALOG_MESSAGE_KEY.value()) + "\n" + ResourceUtil.getMessage(message));

        if (stackTrace != null){
            GridPane expContent = getStackTraceListView(Arrays.toString(stackTrace));
            basicErrorAlert.getDialogPane().setExpandableContent(expContent);
        }

        basicErrorAlert.showAndWait();
    }


    public void createLogoutConfirmationDialog(Stage stage) {
        Alert logoutConfirmationDialog = createBasicInformationAlert();
        logoutConfirmationDialog.setTitle(ResourceUtil.getMessage(ResourceConst.LOGOUT_DIALOG_TITLE_MESSAGE_KEY.value()));
        logoutConfirmationDialog.setHeaderText(ResourceUtil.getMessage(ResourceConst.LOGOUT_DIALOG_HEADER_MESSAGE_KEY.value()));
        logoutConfirmationDialog.showAndWait();
        if (logoutConfirmationDialog.getResult() == YES_BUTTON){
            stage.close();
            AuthorizationUtils.clearAuthorizationData();
            AuthorizationUtils.loadAuthorizationPage();
        }
    }

    public void createSuccessInformationDialog(String message){
        Alert basicSuccessAlert = createBasicSuccessAlert();
        basicSuccessAlert.setHeaderText(ResourceUtil.getMessage(ResourceConst.SUCCESS_DIALOG_HEADER_MESSAGE_KEY.value()));
        if (message != null) {
            basicSuccessAlert.setContentText(ResourceUtil.getMessage(message));
        }else{
            basicSuccessAlert.setContentText(ResourceUtil.getMessage(ResourceConst.SUCCESS_DIALOG_CONTENT_MESSAGE_KEY.value()));
        }
        basicSuccessAlert.showAndWait();
    }

    private Alert createBasicSuccessAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, null, OK_BUTTON);
        createBasicAlert(alert, successIcon);
        alert.setTitle(ResourceUtil.getMessage(ResourceConst.SUCCESS_DIALOG_TITLE_MESSAGE_KEY.value()));
        return alert;
    }

    private Alert createBasicInformationAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, null, NO_BUTTON,YES_BUTTON);
        createBasicAlert(alert, informationIcon);
        return alert;
    }

    private void createBasicAlert(Alert alert, ImageView informationIcon) {
        alert.initStyle(StageStyle.UTILITY);
        Stage stage = (Stage) Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
        if (stage != null){
            alert.initOwner(stage);
        }
        alert.getDialogPane().getStylesheets().add(alertStylesPath);
        alert.getDialogPane().setGraphic(informationIcon);
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
