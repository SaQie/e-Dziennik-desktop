package pl.edziennik.client.common.controller.columns;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import pl.edziennik.client.controller.model.admin.AdminListModel;
import pl.edziennik.client.controller.model.admin.SchoolListModel;
import pl.edziennik.client.controller.model.admin.StudentListModel;
import pl.edziennik.client.controller.model.admin.TeacherListModel;

import java.util.List;

public class AdminTableViewControllerMaker {


    private AdminTableViewControllerMaker() {

    }

    public static SchoolTableViewBuilder schoolTableViewBuilder() {
        return new SchoolTableViewBuilder();
    }

    public static AdministrationTableViewBuilder administrationTableViewBuilder() {
        return new AdministrationTableViewBuilder();
    }

    public static StudentTableViewBuilder studentTableViewBuilder() {
        return new StudentTableViewBuilder();
    }

    public static TeacherTableViewBuilder teacherTableViewBuilder() {
        return new TeacherTableViewBuilder();
    }

    /**
     * Table view builder for administration tab
     */
    public static class AdministrationTableViewBuilder {

        private final List<TableColumn<AdminListModel, ?>> columns = FXCollections.observableArrayList();

        public AdministrationTableViewBuilder withUsernameColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.AdministrationTableViewColumns.getUsernameColumn(isDefaultVisible));
            return this;
        }

        public AdministrationTableViewBuilder withEmailColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.AdministrationTableViewColumns.getEmailColumn(isDefaultVisible));
            return this;
        }

        public AdministrationTableViewBuilder withRoleColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.AdministrationTableViewColumns.getRoleColumn(isDefaultVisible));
            return this;
        }

        public AdministrationTableViewBuilder withSelectionColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.getSelectColumn(isDefaultVisible));
            return this;
        }

        public List<TableColumn<AdminListModel, ?>> build() {
            return columns;
        }
    }

    /**
     * Table view builder for teachers tab
     */
    public static class TeacherTableViewBuilder {

        private final List<TableColumn<TeacherListModel, ?>> columns = FXCollections.observableArrayList();

        public TeacherTableViewBuilder withUsernameColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.TeacherTableViewColumns.getUsernameColumn(isDefaultVisible));
            return this;
        }

        public TeacherTableViewBuilder withRoleColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.TeacherTableViewColumns.getRoleColumn(isDefaultVisible));
            return this;
        }

        public TeacherTableViewBuilder withCityColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.TeacherTableViewColumns.getCityColumn(isDefaultVisible));
            return this;
        }

        public TeacherTableViewBuilder withFirstNameColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.TeacherTableViewColumns.getFirstNameColumn(isDefaultVisible));
            return this;
        }

        public TeacherTableViewBuilder withLastNameColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.TeacherTableViewColumns.getLastNameColumn(isDefaultVisible));
            return this;
        }

        public TeacherTableViewBuilder withPhoneNumberColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.TeacherTableViewColumns.getPhoneNumberColumn(isDefaultVisible));
            return this;
        }

        public TeacherTableViewBuilder withPeselColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.TeacherTableViewColumns.getPeselColumn(isDefaultVisible));
            return this;
        }

        public TeacherTableViewBuilder withAddressColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.TeacherTableViewColumns.getAddressColumn(isDefaultVisible));
            return this;
        }

        public TeacherTableViewBuilder withPostalCodeColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.TeacherTableViewColumns.getPostalCodeColumn(isDefaultVisible));
            return this;
        }

        public TeacherTableViewBuilder withSchoolColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.TeacherTableViewColumns.getSchoolColumn(isDefaultVisible));
            return this;
        }

        public TeacherTableViewBuilder withSelectionColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.getSelectColumn(isDefaultVisible));
            return this;
        }

        public TeacherTableViewBuilder withEmailColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.TeacherTableViewColumns.getEmailColumn(isDefaultVisible));
            return this;
        }


        public List<TableColumn<TeacherListModel, ?>> build() {
            return columns;
        }

    }

    /**
     * Table view builder for students tab
     */
    public static class StudentTableViewBuilder {

        private final List<TableColumn<StudentListModel, ?>> columns = FXCollections.observableArrayList();

        public StudentTableViewBuilder withUsernameColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.StudentTableViewColumns.getUsernameColumn(isDefaultVisible));
            return this;
        }

        public StudentTableViewBuilder withFirstNameColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.StudentTableViewColumns.getFirstNameColumn(isDefaultVisible));
            return this;
        }

        public StudentTableViewBuilder withLastNameColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.StudentTableViewColumns.getLastNameColumn(isDefaultVisible));
            return this;
        }

        public StudentTableViewBuilder withPhoneNumberColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.StudentTableViewColumns.getPhoneNumberColumn(isDefaultVisible));
            return this;
        }

        public StudentTableViewBuilder withPeselColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.StudentTableViewColumns.getPeselColumn(isDefaultVisible));
            return this;
        }

        public StudentTableViewBuilder withParentFullNameColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.StudentTableViewColumns.getParentFullNameColumn(isDefaultVisible));
            return this;
        }

        public StudentTableViewBuilder withAddressColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.StudentTableViewColumns.getAddressColumn(isDefaultVisible));
            return this;
        }

        public StudentTableViewBuilder withPostalCodeColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.StudentTableViewColumns.getPostalCodeColumn(isDefaultVisible));
            return this;
        }

        public StudentTableViewBuilder withRoleColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.StudentTableViewColumns.getRoleColumn(isDefaultVisible));
            return this;
        }

        public StudentTableViewBuilder withSchoolColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.StudentTableViewColumns.getSchoolColumn(isDefaultVisible));
            return this;
        }

        public StudentTableViewBuilder withSchoolClassColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.StudentTableViewColumns.getSchoolClassColumn(isDefaultVisible));
            return this;
        }

        public StudentTableViewBuilder withCityColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.StudentTableViewColumns.getCityColumn(isDefaultVisible));
            return this;
        }

        public StudentTableViewBuilder withSelectionColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.getSelectColumn(isDefaultVisible));
            return this;
        }

        public StudentTableViewBuilder withEmailColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.StudentTableViewColumns.getEmailColumn(isDefaultVisible));
            return this;
        }

        public List<TableColumn<StudentListModel, ?>> build() {
            return columns;
        }

    }


    /**
     * Table view builder for schools tab
     */
    public static class SchoolTableViewBuilder {

        private final List<TableColumn<SchoolListModel, ?>> columns = FXCollections.observableArrayList();

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

        public SchoolTableViewBuilder withHidedSchoolLevelColumn(final boolean isDefaultVisible) {
            columns.add(SchoolTableViewColumns.getSchoolLevelColumn(isDefaultVisible));
            return this;
        }

        public SchoolTableViewBuilder withSelectColumn(final boolean isDefaultVisible) {
            columns.add(SchoolTableViewColumns.getSelectColumn(isDefaultVisible));
            return this;
        }

        public List<TableColumn<SchoolListModel, ?>> build() {
            return columns;
        }

    }


}
