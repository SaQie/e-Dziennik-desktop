package pl.edziennik.client.common.controller.column.admin;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import pl.edziennik.client.common.model.admin.*;

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

    public static ParentsTableViewBuilder parentsTableViewBuilder() {
        return new ParentsTableViewBuilder();
    }

    public static ConfigurationTableViewBuilder configurationTableViewBuilder() {
        return new ConfigurationTableViewBuilder();
    }

    public static SchoolClassTableViewBuilder schoolClassTableViewBuilder() {
        return new SchoolClassTableViewBuilder();
    }

    /**
     * Table view builder for parents tab
     */
    public static class ParentsTableViewBuilder {

        private final List<TableColumn<ParentListModel, ?>> columns = FXCollections.observableArrayList();


        public ParentsTableViewBuilder withCityColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.ParentsTableViewColumns.getCityColumn(isDefaultVisible));
            return this;
        }

        public ParentsTableViewBuilder withFirstNameColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.ParentsTableViewColumns.getFirstNameColumn(isDefaultVisible));
            return this;
        }

        public ParentsTableViewBuilder withLastNameColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.ParentsTableViewColumns.getLastNameColumn(isDefaultVisible));
            return this;
        }

        public ParentsTableViewBuilder withPhoneNumberColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.ParentsTableViewColumns.getPhoneNumberColumn(isDefaultVisible));
            return this;
        }

        public ParentsTableViewBuilder withPeselColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.ParentsTableViewColumns.getPeselColumn(isDefaultVisible));
            return this;
        }

        public ParentsTableViewBuilder withAddressColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.ParentsTableViewColumns.getAddressColumn(isDefaultVisible));
            return this;
        }

        public ParentsTableViewBuilder withPostalCodeColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.ParentsTableViewColumns.getPostalCodeColumn(isDefaultVisible));
            return this;
        }

        public ParentsTableViewBuilder withUsernameColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.ParentsTableViewColumns.getUsernameColumn(isDefaultVisible));
            return this;
        }

        public ParentsTableViewBuilder withEmailColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.ParentsTableViewColumns.getEmailColumn(isDefaultVisible));
            return this;
        }

        public ParentsTableViewBuilder withRoleColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.ParentsTableViewColumns.getRoleColumn(isDefaultVisible));
            return this;
        }

        public ParentsTableViewBuilder withStudentColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.ParentsTableViewColumns.getStudentFullNameColumn(isDefaultVisible));
            return this;
        }

        public ParentsTableViewBuilder withSelectionColumn(final boolean isDefaultVisible) {
            columns.add(AccountsTableViewColumns.getSelectColumn(isDefaultVisible));
            return this;
        }

        public List<TableColumn<ParentListModel, ?>> build() {
            return columns;
        }

    }

    /**
     * Builder class for configuration columns
     */
    public static class ConfigurationTableViewBuilder{

        private final List<TableColumn<ConfigurationListModel, ?>> columns = FXCollections.observableArrayList();

        public ConfigurationTableViewBuilder withNameColumn(final boolean isDefaultVisible){
            columns.add(ConfigurationTableViewColumns.getConfigurationNameColumn(isDefaultVisible));
            return this;
        }

        public ConfigurationTableViewBuilder withSelectionColumn(final boolean isDefaultVisible) {
            columns.add(ConfigurationTableViewColumns.getSelectColumn(isDefaultVisible));
            return this;
        }

        public List<TableColumn<ConfigurationListModel, ?>> build() {
            return columns;
        }

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
     * Table view builder for school classes tab
     */
    public static class SchoolClassTableViewBuilder{

        private final List<TableColumn<SchoolClassListModel, ?>> columns = FXCollections.observableArrayList();

        public SchoolClassTableViewBuilder withSchoolClassNameColumn(final boolean isDefaultVisible){
            columns.add(SchoolClassTableViewColumns.getSchoolClassNameColumn(isDefaultVisible));
            return this;
        }

        public SchoolClassTableViewBuilder withTeacherFullNameColumn(final boolean isDefaultVisible){
            columns.add(SchoolClassTableViewColumns.getSupervisingTeacherNameColumn(isDefaultVisible));
            return this;
        }

        public SchoolClassTableViewBuilder withSchoolNameColumn(final boolean isDefaultVisible){
            columns.add(SchoolClassTableViewColumns.getSchoolNameColumn(isDefaultVisible));
            return this;
        }

        public SchoolClassTableViewBuilder withSelectColumn(final boolean isDefaultVisible){
            columns.add(SchoolClassTableViewColumns.getSelectColumn(isDefaultVisible));
            return this;
        }

        public List<TableColumn<SchoolClassListModel, ?>> build() {
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

        public SchoolTableViewBuilder withIdentifierColumn(final boolean isDefaultVisible){
            columns.add(SchoolTableViewColumns.getIdentifierColumn(isDefaultVisible));
            return this;
        }

        public List<TableColumn<SchoolListModel, ?>> build() {
            return columns;
        }

    }


}
