package pl.edziennik.client.common.controller.columns;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.controller.model.admin.SchoolListModel;
import pl.edziennik.client.utils.ResourceUtil;

class SchoolTableViewColumns extends BasicTableViewColumns{

    private SchoolTableViewColumns() {
    }

    protected static TableColumn<SchoolListModel, String> getSchoolNameColumn(){
        TableColumn<SchoolListModel, String> nameColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.NAME_COLUMN_KEY.value()));
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        return nameColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolAddressColumn(){
        TableColumn<SchoolListModel, String> addressColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.ADDRESS_COLUMN_KEY.value()));
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress());
        return addressColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolNipColumn(){
        TableColumn<SchoolListModel, String> nipColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.NIP_COLUMN_KEY.value()));
        nipColumn.setCellValueFactory(cellData -> cellData.getValue().getNip());
        return nipColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolRegonColumn(){
        TableColumn<SchoolListModel, String> regonColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.REGON_COLUMN_KEY.value()));
        regonColumn.setCellValueFactory(cellData -> cellData.getValue().getRegon());
        return regonColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolPhoneNumberColumn(){
        TableColumn<SchoolListModel, String> phoneNumberColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.PHONE_COLUMN_KEY.value()));
        phoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getPhoneNumber());
        return phoneNumberColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolCityColumn(){
        TableColumn<SchoolListModel, String> cityColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.CITY_COLUMN_KEY.value()));
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().getCity());
        return cityColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolPostalCodeColumn(){
        TableColumn<SchoolListModel, String> postalCodeColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.POSTAL_CODE_COLUMN_KEY.value()));
        postalCodeColumn.setCellValueFactory(cellData -> cellData.getValue().getPostalCode());
        return postalCodeColumn;
    }

    protected static TableColumn<SchoolListModel, Number> getSchoolLevelColumn(){
        TableColumn<SchoolListModel, Number> schoolLevelNameColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.SCHOOL_LEVEL_NAME_COLUMN_KEY.value()));
        schoolLevelNameColumn.setCellValueFactory(cellData -> cellData.getValue().getIdSchoolLevel());
        schoolLevelNameColumn.setVisible(false);
        return schoolLevelNameColumn;
    }




}
