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

        public SchoolTableViewBuilder withSchoolNameColumn() {
            columns.add(SchoolTableViewColumns.getSchoolNameColumn());
            return this;
        }

        public SchoolTableViewBuilder withSchoolCityColumn() {
            columns.add(SchoolTableViewColumns.getSchoolCityColumn());
            return this;
        }

        public SchoolTableViewBuilder withSchoolPostalCodeColumn() {
            columns.add(SchoolTableViewColumns.getSchoolPostalCodeColumn());
            return this;
        }

        public SchoolTableViewBuilder withSchoolNipColumn() {
            columns.add(SchoolTableViewColumns.getSchoolNipColumn());
            return this;
        }

        public SchoolTableViewBuilder withSchoolRegonColumn() {
            columns.add(SchoolTableViewColumns.getSchoolRegonColumn());
            return this;
        }

        public SchoolTableViewBuilder withSchoolPhoneNumberColumn() {
            columns.add(SchoolTableViewColumns.getSchoolPhoneNumberColumn());
            return this;
        }

        public SchoolTableViewBuilder withSchoolAddressColumn() {
            columns.add(SchoolTableViewColumns.getSchoolAddressColumn());
            return this;
        }

        public List<TableColumn<SchoolListModel, ?>> build() {
            return columns;
        }

    }


}
