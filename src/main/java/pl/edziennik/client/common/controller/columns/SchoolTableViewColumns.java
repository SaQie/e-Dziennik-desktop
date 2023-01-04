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
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        return nameColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolAddressColumn(){
        TableColumn<SchoolListModel, String> addressColumn = new TableColumn<>(resourceBundle.getString(ADDRESS_COLUMN_KEY));
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress());
        return addressColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolNipColumn(){
        TableColumn<SchoolListModel, String> nipColumn = new TableColumn<>(resourceBundle.getString(NIP_COLUMN_KEY));
        nipColumn.setCellValueFactory(cellData -> cellData.getValue().getNip());
        return nipColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolRegonColumn(){
        TableColumn<SchoolListModel, String> regonColumn = new TableColumn<>(resourceBundle.getString(REGON_COLUMN_KEY));
        regonColumn.setCellValueFactory(cellData -> cellData.getValue().getRegon());
        return regonColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolPhoneNumberColumn(){
        TableColumn<SchoolListModel, String> phoneNumberColumn = new TableColumn<>(resourceBundle.getString(PHONE_COLUMN_KEY));
        phoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getPhoneNumber());
        return phoneNumberColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolCityColumn(){
        TableColumn<SchoolListModel, String> cityColumn = new TableColumn<>(resourceBundle.getString(CITY_COLUMN_KEY));
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().getCity());
        return cityColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolPostalCodeColumn(){
        TableColumn<SchoolListModel, String> postalCodeColumn = new TableColumn<>(resourceBundle.getString(POSTAL_CODE_COLUMN_KEY));
        postalCodeColumn.setCellValueFactory(cellData -> cellData.getValue().getPostalCode());
        return postalCodeColumn;
    }

    protected static TableColumn<SchoolListModel, Number> getSchoolLevelColumn(){
        TableColumn<SchoolListModel, Number> schoolLevelNameColumn = new TableColumn<>(resourceBundle.getString(SCHOOL_LEVEL_NAME_COLUMN_KEY));
        schoolLevelNameColumn.setCellValueFactory(cellData -> cellData.getValue().getIdSchoolLevel());
        schoolLevelNameColumn.setVisible(false);
        return schoolLevelNameColumn;
    }


}
