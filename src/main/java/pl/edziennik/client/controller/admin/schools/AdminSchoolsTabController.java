package pl.edziennik.client.controller.admin.schools;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
        initializeTableColumns();
        NodeUtils.enableButtonsIfSelectionModelIsNotEmpty(tableView, editButton, showButton, deleteButton);
        NodeUtils.setTableViewPlaceHolder(tableView);
        initializeAddButtonAction();
    }

    private void initializeAddButtonAction() {
        addButton.setOnAction(button -> {
            NodeUtils.openNewStageAbove(DASHBOARD_ADMIN_SCHOOL_ADD_VIEW_ADDRESS, 500, 600,
                    getActualStage());
        });
    }

    private void initializeTableColumns() {
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
    }


    public void fetchTabData(final List<SchoolPojo> schoolList) {
        List<SchoolListModel> schoolListModels = SchoolListModel.mapPojoToModel(schoolList);
        ObservableList<SchoolListModel> items = tableView.getItems();
        items.addAll(schoolListModels);
        tableView.setItems(items);
    }

    public boolean isTableDataEmpty() {
        return tableView.getItems().isEmpty();
    }

    private Stage getActualStage() {
        return (Stage) tableView.getScene().getWindow();
    }

}
