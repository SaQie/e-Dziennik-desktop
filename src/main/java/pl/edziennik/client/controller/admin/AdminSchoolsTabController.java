package pl.edziennik.client.controller.admin;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import pl.edziennik.client.controller.model.admin.SchoolListModel;
import pl.edziennik.client.rest.pojo.SchoolPojo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminSchoolsTabController implements Initializable {

    @FXML
    private TableView<SchoolListModel> tableView;

    @FXML
    private TableColumn<SchoolListModel, String> addressColumn, cityColumn, nameColumn, nipColumn, phoneNumberColumn,
            postalCodeColumn, regonColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Menu 1");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Menu 2");
        cm.getItems().add(mi2);

        nameColumn.setCellValueFactory((TableColumn.CellDataFeatures<SchoolListModel, String> param) ->
                param.getValue().getName());

        addressColumn.setCellValueFactory((TableColumn.CellDataFeatures<SchoolListModel, String> param) ->
                param.getValue().getAddress());

        cityColumn.setCellValueFactory((TableColumn.CellDataFeatures<SchoolListModel, String> param) ->
                param.getValue().getAddress());

        nipColumn.setCellValueFactory((TableColumn.CellDataFeatures<SchoolListModel, String> param) ->
                param.getValue().getNip());

        regonColumn.setCellValueFactory((TableColumn.CellDataFeatures<SchoolListModel, String> param) ->
                param.getValue().getRegon());

        postalCodeColumn.setCellValueFactory((TableColumn.CellDataFeatures<SchoolListModel, String> param) ->
                param.getValue().getPostalCode());

        phoneNumberColumn.setCellValueFactory((TableColumn.CellDataFeatures<SchoolListModel, String> param) ->
                param.getValue().getPhoneNumber());

        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY){
                if (tableView.getSelectionModel().getSelectedItem() != null){
                    cm.show(tableView,mouseEvent.getScreenX(), mouseEvent.getScreenY());
                }
            }
            if (cm.isShowing()){
                if (mouseEvent.getButton() == MouseButton.PRIMARY){
                    cm.hide();
                }
            }
        });
    }


    protected void fetchTabData(final List<SchoolPojo> schoolList) {
        List<SchoolListModel> schoolListModels = SchoolListModel.mapPojoToModel(schoolList);
        ObservableList<SchoolListModel> items = tableView.getItems();
        items.addAll(schoolListModels);
        tableView.setItems(items);
    }

    protected boolean isTableDataEmpty(){
        return tableView.getItems().isEmpty();
    }

}
