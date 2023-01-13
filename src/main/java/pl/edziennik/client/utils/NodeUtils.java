package pl.edziennik.client.utils;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.SneakyThrows;
import pl.edziennik.client.common.builder.CommonStageBuilder;
import pl.edziennik.client.common.DialogFactory;
import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.configuration.converter.PropertiesLanguageConverter;
import pl.edziennik.client.controller.model.admin.TableViewSelection;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import static pl.edziennik.client.common.builder.CommonStageBuilder.StageBuilder.ShowMode.*;

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
        ResourceBundle resourceBundle = ResourceBundle.getBundle(ResourceConst.MESSAGES_RESOURCES_ADDRESS.value(), PropertiesLoader.readProperty("language", new PropertiesLanguageConverter()));
        return new FXMLLoader(viewLocation,resourceBundle);
    }

    @SneakyThrows
    public static void openNewStageAbove(String viewLocation,String title, int width, int height, Stage actualStage){
        CommonStageBuilder.builder()
                .withWidth(width)
                .withHeight(height)
                .withView(viewLocation)
                .withStyle(StageStyle.UTILITY)
                .withFocusRequest(true)
                .withResizable(false)
                .withOwner(actualStage)
                .withSetPositionToCenter(true)
                .withTitle(ResourceUtil.getMessage(title))
                .withShowMode(OPEN_ABOVE)
                .build();
    }

    @SneakyThrows
    public static <T> T getController(String viewLocation, T controller){
        FXMLLoader loader = getLoaderWithResources(NodeUtils.class.getResource(viewLocation));
        loader.load();
        controller = loader.getController();
        return controller;
    }

    public static <T> TableColumn<T, ?> getTableColumnByName(TableView<T> tableView, String name) {
        for (TableColumn<T, ?> column : tableView.getColumns())
            if (column.getText().equals(name)){
                return column ;
            }
        return null ;
    }

    public static void setTextFieldAsNumbersOnly(TextField textField){
        textField.setTextFormatter(getNumberTextFormatter());
    }

    public static void setTextFieldAsNumbersOnly(TextField... textFields){
        Arrays.stream(textFields).forEach(textField -> {
            textField.setTextFormatter(getNumberTextFormatter());
        });
    }

    public static <T> boolean isColumnVisible(TableView<T> tableView, String name){
        for (TableColumn<T, ?> column : tableView.getColumns())
            if (column.getText().equals(name)){
                return column.isVisible();
            }
        return false ;
    }

    public static <T> void enableButtonsIfSelectionModelIsNotEmpty(TableView<T> tableView, ButtonBase... buttons){
        for (ButtonBase button : buttons) {
            button.disableProperty().bind(Bindings.isNull(tableView.getSelectionModel().selectedItemProperty()));
        }
    }

    public static void enableButtonIfFieldsHasNoErrors(Button button, TextField... textFields){
        button.setDisable(true);

        for (TextField input : textFields) {
            input.tooltipProperty().addListener(field -> {
                boolean hasErrors = Arrays.stream(textFields)
                        .allMatch(e -> (!e.getText().isEmpty() || !e.getText().isBlank()) && e.getTooltip() == null);
                button.setDisable(!hasErrors);
            });
        }
    }

    public static <T> void setTableViewPlaceHolder(TableView<T> tableView) {
        tableView.setPlaceholder(new Label(ResourceUtil.getMessage(ResourceConst.TABLE_VIEW_PLACEHOLDER_MESSAGE_KEY.value())));
    }

    private static TextFormatter<String> getNumberTextFormatter(){
        UnaryOperator<TextFormatter.Change> numberOnlyFilter = change -> {
            String text = change.getText();

            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        return new TextFormatter<>(numberOnlyFilter);
    }




    public static<T extends TableViewSelection> void setSelectionAfterClick(TableView<T> tableView) {
        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                tableView.getSelectionModel().getSelectedItem().setSelection();
            }
        });

    }

    public static<T> void enableMenuItemsIfSelectionModelIsNotEmpty(TableView<T> tableView, MenuItem... menuItems) {
        for (MenuItem menuItem : menuItems) {
            menuItem.disableProperty().bind(Bindings.isNull(tableView.getSelectionModel().selectedItemProperty()));
        }
    }

}
