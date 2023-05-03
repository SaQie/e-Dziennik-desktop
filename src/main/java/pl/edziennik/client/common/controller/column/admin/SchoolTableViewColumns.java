package pl.edziennik.client.common.controller.columns.admin;

import javafx.scene.control.TableColumn;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.controller.columns.BasicTableViewColumns;
import pl.edziennik.client.common.model.admin.SchoolListModel;
import pl.edziennik.client.utils.ResourceUtil;

class SchoolTableViewColumns extends BasicTableViewColumns {

    private SchoolTableViewColumns() {
    }

    protected static TableColumn<SchoolListModel, String> getSchoolNameColumn(final boolean isDefaultVisible){
        TableColumn<SchoolListModel, String> nameColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.NAME_COLUMN_KEY.value()));
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        nameColumn.setVisible(isDefaultVisible);
        return nameColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolAddressColumn(final boolean isDefaultVisible){
        TableColumn<SchoolListModel, String> addressColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.ADDRESS_COLUMN_KEY.value()));
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress());
        addressColumn.setVisible(isDefaultVisible);
        return addressColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolNipColumn(final boolean isDefaultVisible){
        TableColumn<SchoolListModel, String> nipColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.NIP_COLUMN_KEY.value()));
        nipColumn.setCellValueFactory(cellData -> cellData.getValue().getNip());
        nipColumn.setVisible(isDefaultVisible);
        return nipColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolRegonColumn(final boolean isDefaultVisible){
        TableColumn<SchoolListModel, String> regonColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.REGON_COLUMN_KEY.value()));
        regonColumn.setCellValueFactory(cellData -> cellData.getValue().getRegon());
        regonColumn.setVisible(isDefaultVisible);
        return regonColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolPhoneNumberColumn(final boolean isDefaultVisible){
        TableColumn<SchoolListModel, String> phoneNumberColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.PHONE_COLUMN_KEY.value()));
        phoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getPhoneNumber());
        phoneNumberColumn.setVisible(isDefaultVisible);
        return phoneNumberColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolCityColumn(final boolean isDefaultVisible){
        TableColumn<SchoolListModel, String> cityColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.CITY_COLUMN_KEY.value()));
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().getCity());
        cityColumn.setVisible(isDefaultVisible);
        return cityColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolPostalCodeColumn(final boolean isDefaultVisible){
        TableColumn<SchoolListModel, String> postalCodeColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.POSTAL_CODE_COLUMN_KEY.value()));
        postalCodeColumn.setCellValueFactory(cellData -> cellData.getValue().getPostalCode());
        postalCodeColumn.setVisible(isDefaultVisible);
        return postalCodeColumn;
    }

    protected static TableColumn<SchoolListModel, String> getSchoolLevelColumn(final boolean isDefaultVisible){
        TableColumn<SchoolListModel, String> schoolLevelNameColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.SCHOOL_LEVEL_NAME_COLUMN_KEY.value()));
        schoolLevelNameColumn.setCellValueFactory(cellData -> cellData.getValue().getSchoolLevel());
        schoolLevelNameColumn.setVisible(isDefaultVisible);
        return schoolLevelNameColumn;
    }





}
