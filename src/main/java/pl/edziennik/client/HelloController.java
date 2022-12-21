package pl.edziennik.client;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TableColumn<String, String> firstColumn;

    @FXML
    private TableColumn<String, String> fourthColumn;

    @FXML
    private TableColumn<String, String> secondColumn;

    @FXML
    private TableView<String> testTable;

    @FXML
    private TableColumn<String, String> thirdColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        firstColumn.setCellValueFactory(c -> new SimpleStringProperty("Dupa"));
//
//        secondColumn.setCellValueFactory(c -> new SimpleStringProperty("Nazwisko"));
//        thirdColumn.setCellValueFactory(c -> new SimpleStringProperty("Pesel"));
//        fourthColumn.setCellValueFactory(c -> new SimpleStringProperty("Adres"));
//
//        ObservableList<String> strings = FXCollections.observableList(List.of("asd", "qwe", "erer", "dvdv"));
//        testTable.setItems(strings);

    }
}