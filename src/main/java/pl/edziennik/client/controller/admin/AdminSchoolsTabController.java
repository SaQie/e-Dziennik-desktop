package pl.edziennik.client.controller.admin;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import pl.edziennik.client.common.controller.columns.TableViewControllerMaker;
import pl.edziennik.client.controller.model.admin.SchoolListModel;
import pl.edziennik.client.rest.pojo.SchoolPojo;
import pl.edziennik.client.utils.NodeUtils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static pl.edziennik.client.common.ResourcesConstants.*;

public class AdminSchoolsTabController implements Initializable {

    @FXML
    private TableView<SchoolListModel> tableView;

    @FXML
    private Button addButton, editButton, showButton, deleteButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TableViewControllerMaker.SchoolTableViewBuilder builder = TableViewControllerMaker.builder()
                .withSchoolCityColumn()
                .withSchoolNameColumn()
                .withSchoolPhoneNumberColumn()
                .withSchoolAddressColumn()
                .withSchoolNipColumn()
                .withSchoolRegonColumn()
                .withSchoolPostalCodeColumn()
                .withHidedSchoolLevelColumn();

        tableView.getColumns().addAll(builder.build());

        NodeUtils.enableButtonsIfSelectionModelIsNotEmpty(tableView,editButton,showButton,deleteButton);

        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Pokaz/ukryj poziom szkol");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Menu 2");
        cm.getItems().add(mi2);

        mi1.setOnAction(button -> {
            TableColumn<SchoolListModel, ?> tableColumnByName = NodeUtils.getTableColumnByName(tableView, resourceBundle.getString(SCHOOL_LEVEL_NAME_COLUMN_KEY));
            tableColumnByName.setVisible(!tableColumnByName.isVisible());
        });

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

    protected boolean isTableDataEmpty() {
        return tableView.getItems().isEmpty();
    }

}
