package pl.edziennik.client.utils;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edziennik.client.common.DialogFactory;
import pl.edziennik.client.controller.model.admin.SchoolListModel;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import static pl.edziennik.client.common.ResourcesConstants.MESSAGES_RESOURCES_ADDRESS;

public class NodeUtils {

    private static DialogFactory dialogFactory = DialogFactory.getInstance();

    public static void createTimer(Label displayTime){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                displayTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd HH:mm")));
            }
        };
        timer.start();
    }

    public static void createExitButtonAction(Button exitButton) {
        exitButton.setOnAction(button -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            dialogFactory.createExitConfirmationDialog(stage);
        });
    }


    public static void createLogoutButton(Button logoutButton) {
        logoutButton.setOnAction(button -> {
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            dialogFactory.createLogoutConfirmationDialog(stage);
        });
    }

    public static FXMLLoader getLoaderWithResources(URL viewLocation){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES_RESOURCES_ADDRESS, new Locale("en", "EN"));
        return new FXMLLoader(viewLocation,resourceBundle);
    }

    public static <T> TableColumn<T, ?> getTableColumnByName(TableView<T> tableView, String name) {
        for (TableColumn<T, ?> column : tableView.getColumns())
            if (column.getText().equals(name)){
                return column ;
            }
        return null ;
    }

    public static <T> boolean isColumnVisible(TableView<T> tableView, String name){
        for (TableColumn<T, ?> column : tableView.getColumns())
            if (column.getText().equals(name)){
                return column.isVisible();
            }
        return false ;
    }

    public static <T> void enableButtonsIfSelectionModelIsNotEmpty(TableView<T> tableView, Button... buttons){
        for (Button button : buttons) {
            button.disableProperty().bind(Bindings.isNull(tableView.getSelectionModel().selectedItemProperty()));
        }
    }

}
