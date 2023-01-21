package pl.edziennik.client.common.controller.columns;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import pl.edziennik.client.controller.model.admin.SchoolListModel;

import java.util.List;

public class TableViewControllerMaker {


    private TableViewControllerMaker() {

    }

    public static SchoolTableViewBuilder builder() {
        return new SchoolTableViewBuilder();
    }


    public static class SchoolTableViewBuilder {

        private List<TableColumn<SchoolListModel, ?>> columns = FXCollections.observableArrayList();

        public SchoolTableViewBuilder withSchoolNameColumn(final boolean isDefaultVisible) {
            columns.add(SchoolTableViewColumns.getSchoolNameColumn(isDefaultVisible));
            return this;
        }

        public SchoolTableViewBuilder withSchoolCityColumn(final boolean isDefaultVisible) {
            columns.add(SchoolTableViewColumns.getSchoolCityColumn(isDefaultVisible));
            return this;
        }

        public SchoolTableViewBuilder withSchoolPostalCodeColumn(final boolean isDefaultVisible) {
            columns.add(SchoolTableViewColumns.getSchoolPostalCodeColumn(isDefaultVisible));
            return this;
        }

        public SchoolTableViewBuilder withSchoolNipColumn(final boolean isDefaultVisible) {
            columns.add(SchoolTableViewColumns.getSchoolNipColumn(isDefaultVisible));
            return this;
        }

        public SchoolTableViewBuilder withSchoolRegonColumn(final boolean isDefaultVisible) {
            columns.add(SchoolTableViewColumns.getSchoolRegonColumn(isDefaultVisible));
            return this;
        }

        public SchoolTableViewBuilder withSchoolPhoneNumberColumn(final boolean isDefaultVisible) {
            columns.add(SchoolTableViewColumns.getSchoolPhoneNumberColumn(isDefaultVisible));
            return this;
        }

        public SchoolTableViewBuilder withSchoolAddressColumn(final boolean isDefaultVisible) {
            columns.add(SchoolTableViewColumns.getSchoolAddressColumn(isDefaultVisible));
            return this;
        }

        public SchoolTableViewBuilder withHidedSchoolLevelColumn(final boolean isDefaultVisible){
            columns.add(SchoolTableViewColumns.getSchoolLevelColumn(isDefaultVisible));
            return this;
        }

        public SchoolTableViewBuilder withSelectColumn(final boolean isDefaultVisible){
            columns.add(SchoolTableViewColumns.getSelectColumn(isDefaultVisible));
            return this;
        }

        public List<TableColumn<SchoolListModel, ?>> build() {
            return columns;
        }

    }


}
