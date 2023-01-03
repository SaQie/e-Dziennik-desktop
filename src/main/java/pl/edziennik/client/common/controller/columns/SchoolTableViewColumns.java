package pl.edziennik.client.common.controller.columns;

import javafx.scene.control.TableColumn;
import pl.edziennik.client.controller.model.admin.SchoolListModel;

import java.util.ResourceBundle;

import static pl.edziennik.client.common.ResourcesConstants.*;

class SchoolTableViewColumns {


    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES_RESOURCES_ADDRESS);

    private SchoolTableViewColumns() {
    }


    protected static TableColumn<SchoolListModel, String> getSchoolNameColumn(){
        TableColumn<SchoolListModel, String> nameColumn = new TableColumn<>(resourceBundle.getString(NAME_COLUMN_KEY));
        nameColumn.setCellValueFactory((TableColumn.CellDataFeatures<SchoolListModel, String> param) ->
                param.getValue().getName());
        return nameColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolAddressColumn(){
        TableColumn<SchoolListModel, String> addressColumn = new TableColumn<>(resourceBundle.getString(ADDRESS_COLUMN_KEY));
        addressColumn.setCellValueFactory((TableColumn.CellDataFeatures<SchoolListModel, String> param) ->
                param.getValue().getAddress());
        return addressColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolNipColumn(){
        TableColumn<SchoolListModel, String> nipColumn = new TableColumn<>(resourceBundle.getString(NIP_COLUMN_KEY));
        nipColumn.setCellValueFactory((TableColumn.CellDataFeatures<SchoolListModel, String> param) ->
                param.getValue().getNip());
        return nipColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolRegonColumn(){
        TableColumn<SchoolListModel, String> regonColumn = new TableColumn<>(resourceBundle.getString(REGON_COLUMN_KEY));
        regonColumn.setCellValueFactory((TableColumn.CellDataFeatures<SchoolListModel, String> param) ->
                param.getValue().getRegon());
        return regonColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolPhoneNumberColumn(){
        TableColumn<SchoolListModel, String> phoneNumberColumn = new TableColumn<>(resourceBundle.getString(PHONE_COLUMN_KEY));
        phoneNumberColumn.setCellValueFactory((TableColumn.CellDataFeatures<SchoolListModel, String> param) ->
                param.getValue().getPhoneNumber());
        return phoneNumberColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolCityColumn(){
        TableColumn<SchoolListModel, String> cityColumn = new TableColumn<>(resourceBundle.getString(CITY_COLUMN_KEY));
        cityColumn.setCellValueFactory((TableColumn.CellDataFeatures<SchoolListModel, String> param) ->
                param.getValue().getCity());
        return cityColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolPostalCodeColumn(){
        TableColumn<SchoolListModel, String> postalCodeColumn = new TableColumn<>(resourceBundle.getString(POSTAL_CODE_COLUMN_KEY));
        postalCodeColumn.setCellValueFactory((TableColumn.CellDataFeatures<SchoolListModel, String> param) ->
                param.getValue().getPostalCode());
        return postalCodeColumn;
    }


}
