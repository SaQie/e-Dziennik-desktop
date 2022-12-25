package pl.edziennik.client.common;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static pl.edziennik.client.common.ResourcesConstants.*;

import java.util.ResourceBundle;

public class ConfirmationDialogFactory {

    private final String alertStylesPath = getClass().getResource(ALERT_STYLES_ADDRESS).toExternalForm();
    private final ImageView informationIcon = new ImageView(getClass().getResource(INFORMATION_ICON_ADDRESS).toExternalForm());
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES_RESOURCES_ADDRESS);

    private static ConfirmationDialogFactory factory;

    private ConfirmationDialogFactory(){
    }

    public static ConfirmationDialogFactory getInstance(){
        if (factory == null){
            factory = new ConfirmationDialogFactory();
        }
        return factory;
    }

    private final String EXIT_CONFIRMATION_DIALOG_MESSAGE = resourceBundle.getString(EXIT_CONFIRMATION_MESSAGE_KEY);
    private final String EXIT_CONFIRMATION_DIALOG_HEADER_MESSAGE = resourceBundle.getString(EXIT_CONFIRMATION_HEADER_KEY);
    private final ButtonType YES_BUTTON = new ButtonType(resourceBundle.getString(BUTTON_YES_KEY));
    private final ButtonType NO_BUTTON = new ButtonType(resourceBundle.getString(BUTTON_NO_KEY));


    public void createExitConfirmationDialog(Stage stage){
        Alert alert = createBasicInformationAlert();
        alert.setTitle(EXIT_CONFIRMATION_DIALOG_HEADER_MESSAGE);
        alert.setHeaderText(EXIT_CONFIRMATION_DIALOG_MESSAGE);
        alert.showAndWait();
        if (alert.getResult() == YES_BUTTON){
            stage.close();
        }
    }

    private Alert createBasicInformationAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, null, NO_BUTTON,YES_BUTTON);
        alert.initStyle(StageStyle.UTILITY);
        alert.getDialogPane().getStylesheets().add(alertStylesPath);
        alert.getDialogPane().setGraphic(informationIcon);
        return alert;
    }

}
