package pl.edziennik.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {


    @FXML
    private ComboBox<String> accountType;

    @FXML
    private AnchorPane anchor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> strings = FXCollections.observableList(List.of("Nauczyciel", "Student"));
        accountType.getItems().addAll(strings);
    }
}