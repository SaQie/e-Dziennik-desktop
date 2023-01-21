package pl.edziennik.client.controller.configuration;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class TableColumnViewConfigController implements Initializable {

    public static final KeyCombination KEY_COMBINATION_SHORTCUT = new KeyCodeCombination(KeyCode.K, KeyCombination.CONTROL_DOWN);

    @FXML
    private VBox vBoxColumns;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public <T> void manageTableColumnVisible(ObservableList<TableColumn<T, ?>> columns) {
        for (TableColumn<T, ?> column : columns) {
            if (column.getText() == null || column.getText().isEmpty() || column.getText().isBlank()) {
                continue;
            }
            fillTableColumnViewConfigData(column);
        }
    }

    private <T> void fillTableColumnViewConfigData(TableColumn<T, ?> column) {
        HBox hBox = new HBox();
        hBox.setSpacing(50);
        hBox.setAlignment(Pos.CENTER);
        Label columnName = new Label(column.getText());
        columnName.setTextFill(Paint.valueOf("white"));
        columnName.setMinWidth(150);
        CheckBox isVisible = new CheckBox();
        hBox.getChildren().add(columnName);
        hBox.getChildren().add(isVisible);
        isVisible.setSelected(column.isVisible());
        vBoxColumns.setAlignment(Pos.CENTER);
        vBoxColumns.setSpacing(10);
        vBoxColumns.getChildren().add(hBox);
        isVisible.selectedProperty().bindBidirectional(column.visibleProperty());
    }
}
