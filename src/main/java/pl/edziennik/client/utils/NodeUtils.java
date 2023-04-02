package pl.edziennik.client.utils;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.concurrent.Task;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.SneakyThrows;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.common.factory.ActionType;
import pl.edziennik.client.common.factory.DialogFactory;
import pl.edziennik.client.common.factory.DictionaryFactory;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.builder.CommonStageBuilder;
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.configuration.converter.PropertiesLanguageConverter;
import pl.edziennik.client.controller.configuration.TableColumnViewConfigController;
import pl.edziennik.client.core.DictionaryItemModel;
import pl.edziennik.client.core.TableSelectionMode;
import pl.edziennik.client.core.TableViewSelection;
import pl.edziennik.client.core.StageManager;
import pl.edziennik.client.exception.TableViewException;
import pl.edziennik.client.rest.dto.DictionaryItemDto;
import pl.edziennik.client.rest.dto.Page;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import static pl.edziennik.client.common.constants.ResourceConst.*;
import static pl.edziennik.client.common.builder.CommonStageBuilder.StageBuilder.ShowMode.OPEN_ABOVE;
import static pl.edziennik.client.common.builder.CommonStageBuilder.StageBuilder.ShowMode.OPEN_ABOVE_AND_RETURN_CONTROLLER;

public class NodeUtils {


    private static DialogFactory dialogFactory = DialogFactory.getInstance();
    private static DictionaryFactory dictionaryFactory = DictionaryFactory.getInstance();

    public static void createTimer(Label displayTime) {
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

    public static void createCancelButtonAction(Button cancelButton) {
        cancelButton.setOnAction(button -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            NodeUtils.closeCurrentStage(stage);
        });
    }


    public static void createLogoutButton(Button logoutButton) {
        logoutButton.setOnAction(button -> {
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            dialogFactory.createLogoutConfirmationDialog(stage);
        });
    }

    public static FXMLLoader getLoaderWithResources(URL viewLocation) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(ResourceConst.MESSAGES_RESOURCES_ADDRESS.value(), PropertiesLoader.readProperty("language", new PropertiesLanguageConverter()));
        return new FXMLLoader(viewLocation, resourceBundle);
    }

    @SneakyThrows
    public static void openNewStageAbove(String viewLocation, String title, int width, int height, Stage actualStage, Button buttonToDisable) {
        CommonStageBuilder.stageBuilder()
                .withWidth(width)
                .withHeight(height)
                .withView(viewLocation)
                .withStyle(StageStyle.UTILITY)
                .withFocusRequest(true)
                .withResizable(false)
                .withOwner(actualStage)
                .withButton(buttonToDisable)
                .withSetPositionToCenter(true)
                .withTitle(title)
                .withShowMode(OPEN_ABOVE)
                .build();
    }

    public static void openNewStageAbove(String viewLocation, String title, int width, int height, Stage stage) {
        CommonStageBuilder.stageBuilder()
                .withWidth(width)
                .withHeight(height)
                .withView(viewLocation)
                .withStyle(StageStyle.UTILITY)
                .withModality(Modality.APPLICATION_MODAL)
                .withFocusRequest(true)
                .withResizable(false)
                .withSetPositionToCenter(true)
                .withTitle(title)
                .withOwner(stage)
                .withShowMode(OPEN_ABOVE)
                .build();
    }

    public static <T> T openNewStageAboveWithController(String viewLocation, String title, int width, int height, Stage stage) {
        return CommonStageBuilder.stageBuilder()
                .withWidth(width)
                .withHeight(height)
                .withView(viewLocation)
                .withStyle(StageStyle.UTILITY)
                .withModality(Modality.APPLICATION_MODAL)
                .withFocusRequest(true)
                .withResizable(false)
                .withSetPositionToCenter(true)
                .withTitle(title)
                .withOwner(stage)
                .withShowMode(OPEN_ABOVE_AND_RETURN_CONTROLLER)
                .build();
    }

    public static <T> T openNewStageAboveWithController(String viewLocation, String title, int width, int height, Button buttonToDisable) {
        return CommonStageBuilder.stageBuilder()
                .withTitle(title)
                .withWidth(width)
                .withHeight(height)
                .withView(viewLocation)
                .withStyle(StageStyle.DECORATED)
                .withModality(Modality.NONE)
                .withFocusRequest(true)
                .withButton(buttonToDisable)
                .withResizable(false)
                .withSearchActualStage(true)
                .withSetPositionToCenter(true)
                .withShowMode(OPEN_ABOVE_AND_RETURN_CONTROLLER)
                .build();
    }

    @SneakyThrows
    public static <T> T getController(String viewLocation, T controller) {
        FXMLLoader loader = getLoaderWithResources(NodeUtils.class.getResource(viewLocation));
        loader.load();
        controller = loader.getController();
        return controller;
    }

    public static <T> TableColumn<T, ?> getTableColumnByName(TableView<T> tableView, String name) {
        for (TableColumn<T, ?> column : tableView.getColumns())
            if (column.getText().equals(name)) {
                return column;
            }
        return null;
    }

    public static void setTextFieldAsNumbersOnly(TextField textField) {
        textField.setTextFormatter(getNumberTextFormatter());
    }

    public static void setTextFieldAsNumbersOnly(TextField... textFields) {
        Arrays.stream(textFields).forEach(textField -> {
            textField.setTextFormatter(getNumberTextFormatter());
        });
    }

    public static <T> boolean isColumnVisible(TableView<T> tableView, String name) {
        for (TableColumn<T, ?> column : tableView.getColumns())
            if (column.getText().equals(name)) {
                return column.isVisible();
            }
        return false;
    }


    public static void enableButtonIfFieldsHasNoErrors(Button button, TextField... textFields) {
        button.setDisable(true);

        for (TextField input : textFields) {
            input.tooltipProperty().addListener(field -> {
                boolean hasErrors = Arrays.stream(textFields)
                        .allMatch(e -> (!e.getText().isEmpty() || !e.getText().isBlank()) && e.getTooltip() == null);
                button.setDisable(!hasErrors);
            });
        }
    }

    public static void enableButtonIfFieldsHasNoErrors(Button button, Control... controls) {
        button.setDisable(true);
        for (Control control : controls) {
            control.tooltipProperty().addListener(field -> {
                boolean hasErrors = Arrays.stream(controls)
                        .allMatch(e -> e.getPseudoClassStates().contains(PseudoClass.getPseudoClass("valid")) && e.getTooltip() == null);
                button.setDisable(!hasErrors);
            });
        }
    }


    public static void enableButtonIfFieldsAreNotEmpty(Button button, TextField... textFields) {
        BooleanBinding firstFieldBooleanBinding = null;
        for (TextField textField : textFields) {
            if (firstFieldBooleanBinding != null) {
                firstFieldBooleanBinding.or(Bindings.isEmpty(textField.textProperty()));
            }
            firstFieldBooleanBinding = Bindings.isEmpty(textField.textProperty());
        }
        button.disableProperty().bind(
                firstFieldBooleanBinding);
    }

    public static <T> void setTableViewPlaceHolder(TableView<T> tableView) {
        tableView.setPlaceholder(new Label(ResourceUtil.getMessage(ResourceConst.TABLE_VIEW_PLACEHOLDER_MESSAGE_KEY.value())));
    }

    private static TextFormatter<String> getNumberTextFormatter() {
        UnaryOperator<TextFormatter.Change> numberOnlyFilter = change -> {
            String text = change.getText();

            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        return new TextFormatter<>(numberOnlyFilter);
    }

    public static <T> void enableMenuItemsIfSelectionModelIsNotEmpty(TableView<T> tableView, MenuItem... menuItems) {
        for (MenuItem menuItem : menuItems) {
            menuItem.disableProperty().bind(Bindings.isNull(tableView.getSelectionModel().selectedItemProperty()));
        }
    }


    public static <T extends TableViewSelection> List<Long> getSelectedTableItems(TableView<T> tableView, ActionType actionType) {
        List<Long> idsSelectedRows = tableView.getItems()
                .stream()
                .filter(TableViewSelection::isSelected)
                .map(TableViewSelection::getId)
                .toList();
        checkSelectedTableRows(actionType, idsSelectedRows);
        return idsSelectedRows;
    }

    public static <T extends DictionaryItemModel> DictionaryItemModel getSelectedDictionaryItem(TableView<T> tableView) {
        List<T> items = tableView.getItems()
                .stream()
                .filter(DictionaryItemModel::isSelected)
                .toList();
        if (items.size() > 1) {
            throw new TableViewException(TABLE_VIEW_TOO_MANY_ROWS_MESSAGE_KEY.value());
        }
        return items.isEmpty() ? null : items.get(0);
    }

    public static <T extends TableViewSelection> List<Long> getSelectedUserIdTableItems(TableView<T> tableView, ActionType actionType) {
        List<Long> idsSelectedRows = tableView.getItems()
                .stream()
                .filter(TableViewSelection::isSelected)
                .map(TableViewSelection::getUserId)
                .toList();
        checkSelectedTableRows(actionType, idsSelectedRows);
        return idsSelectedRows;
    }

    public static <T extends TableViewSelection> void setColumnConfigurationShortcut(TableView<T> tableView) {
        EventHandler<KeyEvent> eventHandler = event -> {
            if (tableView.isHover()) {
                if (TableColumnViewConfigController.KEY_COMBINATION_SHORTCUT.match(event)) {
                    TableColumnViewConfigController controller = openNewStageAboveWithController(TABLE_COLUMN_VIEW_CONFIG_VIEW_ADDRESS.value(),
                            COLUMN_VIEW_CONFIGURATION_TITLE_MESSAGE_KEY.value(), 350, 450, (Stage) tableView.getScene().getWindow());
                    controller.manageTableColumnVisible(tableView.getColumns());
                }
            }
        };

        tableView.sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                oldValue.removeEventFilter(KeyEvent.KEY_PRESSED, eventHandler);
            }
            if (newValue != null) {
                newValue.addEventFilter(KeyEvent.KEY_PRESSED, eventHandler);
            }
        });
    }

    public static <T extends TableViewSelection> void setTableSelectOption(TableView<T> tableView, TableSelectionMode selectionMode) {
        tableView.setRowFactory(factory -> {
            TableRow<T> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton() == MouseButton.PRIMARY && (!row.isEmpty())) {
                    if (TableSelectionMode.SINGLE.equals(selectionMode)){
                        tableView.getItems()
                                .stream()
                                .filter(TableViewSelection::isSelected)
                                .forEach(tableItem -> tableItem.getSelect().setSelected(false));
                    }
                    tableView.getSelectionModel().getSelectedItem().setSelection();
                }
            });
            return row;
        });
    }

    private static void checkSelectedTableRows(ActionType actionType, List<Long> idsSelectedRows) {
        if (idsSelectedRows.isEmpty()) {
            throw new TableViewException(TABLE_VIEW_ROW_NOT_SELECTED_MESSAGE_KEY.value());
        }
        switch (actionType) {
            case EDIT_ACTION, SHOW_ACTION -> {
                if (idsSelectedRows.size() > 1) {
                    throw new TableViewException(TABLE_VIEW_TOO_MANY_ROWS_MESSAGE_KEY.value());
                }
            }
        }
    }

    public static void closeCurrentStage(Stage actualStage) {
        StageManager.close(actualStage);
    }

    public static <T extends DictionaryItemDto, E extends Task<Page<List<T>>>> void initializeDictionary(Class<E> task, ComboBox<DictionaryItemModel> dictionaryComboBox, Long... params) {
        ContextMenu menu = Styles.clearDictionaryAction();
        MenuItem item = menu.getItems().get(0);

        dictionaryComboBox.setOnMouseClicked(event -> {
            dictionaryComboBox.hide();
            if (MouseButton.SECONDARY.equals(event.getButton())){
                menu.show(dictionaryComboBox, event.getScreenX(), event.getScreenY());
                item.setOnAction(action -> {
                    dictionaryComboBox.hide();
                    dictionaryComboBox.getSelectionModel().select(null);
                });
            }
            if (MouseButton.PRIMARY.equals(event.getButton())){
                Optional<DictionaryItemModel> valueFromDictionary = dictionaryFactory.createAndGetDictionaryValue(task, params);
                valueFromDictionary.ifPresent(value -> dictionaryComboBox.getSelectionModel().select(value));
            }
        });

    }
}
