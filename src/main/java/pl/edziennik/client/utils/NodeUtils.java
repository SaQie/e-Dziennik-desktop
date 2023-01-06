package pl.edziennik.client.utils;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.SneakyThrows;
import pl.edziennik.client.common.DialogFactory;
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.configuration.converter.PropertiesLanguageConverter;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static pl.edziennik.client.common.ResourcesConstants.*;

public class NodeUtils {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES_RESOURCES_ADDRESS, PropertiesLoader.readProperty("language", new PropertiesLanguageConverter()));

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

    public static void createCancelButtonAction(Button cancelButton){
        cancelButton.setOnAction(button -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }


    public static void createLogoutButton(Button logoutButton) {
        logoutButton.setOnAction(button -> {
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            dialogFactory.createLogoutConfirmationDialog(stage);
        });
    }

    public static FXMLLoader getLoaderWithResources(URL viewLocation){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES_RESOURCES_ADDRESS, PropertiesLoader.readProperty("language", new PropertiesLanguageConverter()));
        return new FXMLLoader(viewLocation,resourceBundle);
    }

    @SneakyThrows
    public static <T> T openNewStageAbove(String viewLocation, int width, int height, Stage actualStage, T controller){
        FXMLLoader loader = getLoaderWithResources(NodeUtils.class.getResource(viewLocation));
        Scene scene = new Scene(loader.load(), width,height);
        controller = loader.getController();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.requestFocus();
        stage.initOwner(actualStage);
        stage.setX(actualStage.getX() + actualStage.getWidth() / 2 - scene.getWidth() / 2);
        stage.setY(actualStage.getY() + actualStage.getHeight() / 2 - scene.getHeight() / 2);
        stage.setScene(scene);
        stage.show();
        return controller;
    }

    @SneakyThrows
    public static void openNewStageAbove(String viewLocation, int width, int height, Stage actualStage){
        FXMLLoader loader = getLoaderWithResources(NodeUtils.class.getResource(viewLocation));
        Scene scene = new Scene(loader.load(), width,height);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.requestFocus();
        stage.setResizable(false);
        stage.initOwner(actualStage);
        stage.setX(actualStage.getX() + actualStage.getWidth() / 2 - scene.getWidth() / 2);
        stage.setY(actualStage.getY() + actualStage.getHeight() / 2 - scene.getHeight() / 2);
        stage.setScene(scene);
        stage.show();
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

    public static <T> void setTableViewPlaceHolder(TableView<T> tableView) {
        tableView.setPlaceholder(new Label(resourceBundle.getString(TABLE_VIEW_PLACEHOLDER_MESSAGE_KEY)));
//        tableView.setPlaceholder(new Label(resourceBundle.getString(TABLE_VIEW_PLACEHOLDER_MESSAGE_KEY)));
    }
}
