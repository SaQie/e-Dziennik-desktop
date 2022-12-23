package pl.edziennik.client;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

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

    @FXML
    private TreeView<String> treeView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TreeItem<String> rootItem = new TreeItem<>("Szkoły");

        TreeItem<String> branchItem1 = new TreeItem<>("Szkola podstawowa nr. 1");
        TreeItem<String> branchItem2 = new TreeItem<>("Szkola podstawowa nr. 2");
        TreeItem<String> detailItem1 = new TreeItem<>("1A");
        TreeItem<String> detailItem2 = new TreeItem<>("2A");
        TreeItem<String> detailItem3 = new TreeItem<>("3A");

        rootItem.getChildren().setAll(branchItem1, branchItem2);

        branchItem1.getChildren().setAll(detailItem1, detailItem2,detailItem3);
        treeView.setRoot(rootItem);



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