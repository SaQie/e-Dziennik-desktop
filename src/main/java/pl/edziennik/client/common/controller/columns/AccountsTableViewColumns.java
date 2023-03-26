package pl.edziennik.client.common.controller.columns;

import javafx.scene.control.TableColumn;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.controller.model.admin.AdminListModel;
import pl.edziennik.client.controller.model.admin.ParentListModel;
import pl.edziennik.client.controller.model.admin.StudentListModel;
import pl.edziennik.client.controller.model.admin.TeacherListModel;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.*;

class AccountsTableViewColumns extends BasicTableViewColumns {

    private AccountsTableViewColumns() {
    }

    protected static class AdministrationTableViewColumns {

        protected static TableColumn<AdminListModel, String> getUsernameColumn(final boolean isDefaultVisible) {
            TableColumn<AdminListModel, String> usernameColumn = new TableColumn<>(ResourceUtil.getMessage(USERNAME_COLUMN_KEY.value()));
            usernameColumn.setCellValueFactory(cellData -> cellData.getValue().getUsername());
            usernameColumn.setVisible(isDefaultVisible);
            return usernameColumn;
        }

        protected static TableColumn<AdminListModel, String> getEmailColumn(final boolean isDefaultVisible) {
            TableColumn<AdminListModel, String> emailColumn = new TableColumn<>(ResourceUtil.getMessage(EMAIL_COLUMN_KEY.value()));
            emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmail());
            emailColumn.setVisible(isDefaultVisible);
            return emailColumn;
        }

        protected static TableColumn<AdminListModel, String> getRoleColumn(final boolean isDefaultVisible) {
            TableColumn<AdminListModel, String> roleColumn = new TableColumn<>(ResourceUtil.getMessage(ROLE_COLUMN_KEY.value()));
            roleColumn.setCellValueFactory(cellData -> cellData.getValue().getRole());
            roleColumn.setVisible(isDefaultVisible);
            return roleColumn;
        }

    }

    protected static class ParentsTableViewColumns{

        protected static TableColumn<ParentListModel, String> getRoleColumn(final boolean isDefaultVisible){
            TableColumn<ParentListModel, String> roleColumn = new TableColumn<>(ResourceUtil.getMessage(ROLE_COLUMN_KEY.value()));
            roleColumn.setCellValueFactory(cellData -> cellData.getValue().getRole());
            roleColumn.setVisible(isDefaultVisible);
            return roleColumn;
        }

        protected static TableColumn<ParentListModel, String> getUsernameColumn(final boolean isDefaultVisible) {
            TableColumn<ParentListModel, String> usernameColumn = new TableColumn<>(ResourceUtil.getMessage(USERNAME_COLUMN_KEY.value()));
            usernameColumn.setCellValueFactory(cellData -> cellData.getValue().getUsername());
            usernameColumn.setVisible(isDefaultVisible);
            return usernameColumn;
        }

        protected static TableColumn<ParentListModel, String> getFirstNameColumn(final boolean isDefaultVisible) {
            TableColumn<ParentListModel, String> firstNameColumn = new TableColumn<>(ResourceUtil.getMessage(FIRST_NAME_COLUMN_KEY.value()));
            firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
            firstNameColumn.setVisible(isDefaultVisible);
            return firstNameColumn;
        }

        protected static TableColumn<ParentListModel, String> getLastNameColumn(final boolean isDefaultVisible) {
            TableColumn<ParentListModel, String> lastNameColumn = new TableColumn<>(ResourceUtil.getMessage(LAST_NAME_COLUMN_KEY.value()));
            lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastName());
            lastNameColumn.setVisible(isDefaultVisible);
            return lastNameColumn;
        }

        protected static TableColumn<ParentListModel, String> getPhoneNumberColumn(final boolean isDefaultVisible) {
            TableColumn<ParentListModel, String> lastNameColumn = new TableColumn<>(ResourceUtil.getMessage(PHONE_COLUMN_KEY.value()));
            lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getPhoneNumber());
            lastNameColumn.setVisible(isDefaultVisible);
            return lastNameColumn;
        }


        protected static TableColumn<ParentListModel, String> getAddressColumn(final boolean isDefaultVisible) {
            TableColumn<ParentListModel, String> addressColumn = new TableColumn<>(ResourceUtil.getMessage(ADDRESS_COLUMN_KEY.value()));
            addressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress());
            addressColumn.setVisible(isDefaultVisible);
            return addressColumn;
        }

        protected static TableColumn<ParentListModel, String> getPostalCodeColumn(final boolean isDefaultVisible) {
            TableColumn<ParentListModel, String> postalCodeColumn = new TableColumn<>(ResourceUtil.getMessage(POSTAL_CODE_COLUMN_KEY.value()));
            postalCodeColumn.setCellValueFactory(cellData -> cellData.getValue().getPostalCode());
            postalCodeColumn.setVisible(isDefaultVisible);
            return postalCodeColumn;
        }

        protected static TableColumn<ParentListModel, String> getCityColumn(final boolean isDefaultVisible) {
            TableColumn<ParentListModel, String> cityColumn = new TableColumn<>(ResourceUtil.getMessage(CITY_COLUMN_KEY.value()));
            cityColumn.setCellValueFactory(cellData -> cellData.getValue().getCity());
            cityColumn.setVisible(isDefaultVisible);
            return cityColumn;
        }

        protected static TableColumn<ParentListModel, String> getPeselColumn(final boolean isDefaultVisible) {
            TableColumn<ParentListModel, String> peselColumn = new TableColumn<>(ResourceUtil.getMessage(PESEL_COLUMN_KEY.value()));
            peselColumn.setCellValueFactory(cellData -> cellData.getValue().getPesel());
            peselColumn.setVisible(isDefaultVisible);
            return peselColumn;
        }

        public static TableColumn<ParentListModel, String> getEmailColumn(boolean isDefaultVisible) {
            TableColumn<ParentListModel, String> emailColumn = new TableColumn<>(ResourceUtil.getMessage(EMAIL_COLUMN_KEY.value()));
            emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmail());
            emailColumn.setVisible(isDefaultVisible);
            return emailColumn;
        }

        public static TableColumn<ParentListModel, String> getStudentFullNameColumn(boolean isDefaultVisible) {
            TableColumn<ParentListModel, String> studentFullNameColumn = new TableColumn<>(ResourceUtil.getMessage(STUDENT_NAME_COLUMN_KEY.value()));
            studentFullNameColumn.setCellValueFactory(cellData -> cellData.getValue().getStudentFullName());
            studentFullNameColumn.setVisible(isDefaultVisible);
            return studentFullNameColumn;
        }

    }

    protected static class StudentTableViewColumns {

        protected static TableColumn<StudentListModel, String> getRoleColumn(final boolean isDefaultVisible) {
            TableColumn<StudentListModel, String> roleColumn = new TableColumn<>(ResourceUtil.getMessage(ROLE_COLUMN_KEY.value()));
            roleColumn.setCellValueFactory(cellData -> cellData.getValue().getRole());
            roleColumn.setVisible(isDefaultVisible);
            return roleColumn;
        }

        protected static TableColumn<StudentListModel, String> getUsernameColumn(final boolean isDefaultVisible) {
            TableColumn<StudentListModel, String> usernameColumn = new TableColumn<>(ResourceUtil.getMessage(USERNAME_COLUMN_KEY.value()));
            usernameColumn.setCellValueFactory(cellData -> cellData.getValue().getUsername());
            usernameColumn.setVisible(isDefaultVisible);
            return usernameColumn;
        }

        protected static TableColumn<StudentListModel, String> getFirstNameColumn(final boolean isDefaultVisible) {
            TableColumn<StudentListModel, String> firstNameColumn = new TableColumn<>(ResourceUtil.getMessage(FIRST_NAME_COLUMN_KEY.value()));
            firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
            firstNameColumn.setVisible(isDefaultVisible);
            return firstNameColumn;
        }

        protected static TableColumn<StudentListModel, String> getLastNameColumn(final boolean isDefaultVisible) {
            TableColumn<StudentListModel, String> lastNameColumn = new TableColumn<>(ResourceUtil.getMessage(LAST_NAME_COLUMN_KEY.value()));
            lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastName());
            lastNameColumn.setVisible(isDefaultVisible);
            return lastNameColumn;
        }

        protected static TableColumn<StudentListModel, String> getPhoneNumberColumn(final boolean isDefaultVisible) {
            TableColumn<StudentListModel, String> lastNameColumn = new TableColumn<>(ResourceUtil.getMessage(PHONE_COLUMN_KEY.value()));
            lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getPhoneNumber());
            lastNameColumn.setVisible(isDefaultVisible);
            return lastNameColumn;
        }


        protected static TableColumn<StudentListModel, String> getAddressColumn(final boolean isDefaultVisible) {
            TableColumn<StudentListModel, String> addressColumn = new TableColumn<>(ResourceUtil.getMessage(ADDRESS_COLUMN_KEY.value()));
            addressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress());
            addressColumn.setVisible(isDefaultVisible);
            return addressColumn;
        }

        protected static TableColumn<StudentListModel, String> getPostalCodeColumn(final boolean isDefaultVisible) {
            TableColumn<StudentListModel, String> postalCodeColumn = new TableColumn<>(ResourceUtil.getMessage(POSTAL_CODE_COLUMN_KEY.value()));
            postalCodeColumn.setCellValueFactory(cellData -> cellData.getValue().getPostalCode());
            postalCodeColumn.setVisible(isDefaultVisible);
            return postalCodeColumn;
        }

        protected static TableColumn<StudentListModel, String> getCityColumn(final boolean isDefaultVisible) {
            TableColumn<StudentListModel, String> cityColumn = new TableColumn<>(ResourceUtil.getMessage(CITY_COLUMN_KEY.value()));
            cityColumn.setCellValueFactory(cellData -> cellData.getValue().getCity());
            cityColumn.setVisible(isDefaultVisible);
            return cityColumn;
        }

        protected static TableColumn<StudentListModel, String> getPeselColumn(final boolean isDefaultVisible) {
            TableColumn<StudentListModel, String> peselColumn = new TableColumn<>(ResourceUtil.getMessage(PESEL_COLUMN_KEY.value()));
            peselColumn.setCellValueFactory(cellData -> cellData.getValue().getPesel());
            peselColumn.setVisible(isDefaultVisible);
            return peselColumn;
        }

        protected static TableColumn<StudentListModel, String> getParentFullNameColumn(final boolean isDefaultVisible) {
            TableColumn<StudentListModel, String> parentFullName = new TableColumn<>(ResourceUtil.getMessage(PARENT_FULL_NAME_COLUMN_KEY.value()));
            parentFullName.setCellValueFactory(cellData -> cellData.getValue().getParentFullName());
            parentFullName.setVisible(isDefaultVisible);
            return parentFullName;
        }

        protected static TableColumn<StudentListModel, String> getSchoolColumn(final boolean isDefaultVisible) {
            TableColumn<StudentListModel, String> schoolColumn = new TableColumn<>(ResourceUtil.getMessage(SCHOOL_COLUMN_KEY.value()));
            schoolColumn.setCellValueFactory(cellData -> cellData.getValue().getSchool());
            schoolColumn.setVisible(isDefaultVisible);
            return schoolColumn;
        }

        protected static TableColumn<StudentListModel, String> getSchoolClassColumn(final boolean isDefaultVisible) {
            TableColumn<StudentListModel, String> schoolClassColumn = new TableColumn<>(ResourceUtil.getMessage(SCHOOL_CLASS_COLUMN_KEY.value()));
            schoolClassColumn.setCellValueFactory(cellData -> cellData.getValue().getSchoolClass());
            schoolClassColumn.setVisible(isDefaultVisible);
            return schoolClassColumn;
        }

        public static TableColumn<StudentListModel, String> getEmailColumn(boolean isDefaultVisible) {
            TableColumn<StudentListModel, String> emailColumn = new TableColumn<>(ResourceUtil.getMessage(EMAIL_COLUMN_KEY.value()));
            emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmail());
            emailColumn.setVisible(isDefaultVisible);
            return emailColumn;
        }
    }

    protected static class TeacherTableViewColumns {

        protected static TableColumn<TeacherListModel, String> getRoleColumn(final boolean isDefaultVisible) {
            TableColumn<TeacherListModel, String> roleColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.ROLE_COLUMN_KEY.value()));
            roleColumn.setCellValueFactory(cellData -> cellData.getValue().getRole());
            roleColumn.setVisible(isDefaultVisible);
            return roleColumn;
        }

        protected static TableColumn<TeacherListModel, String> getUsernameColumn(final boolean isDefaultVisible) {
            TableColumn<TeacherListModel, String> usernameColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.USERNAME_COLUMN_KEY.value()));
            usernameColumn.setCellValueFactory(cellData -> cellData.getValue().getUsername());
            usernameColumn.setVisible(isDefaultVisible);
            return usernameColumn;
        }

        protected static TableColumn<TeacherListModel, String> getFirstNameColumn(final boolean isDefaultVisible) {
            TableColumn<TeacherListModel, String> firstNameColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.FIRST_NAME_COLUMN_KEY.value()));
            firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
            firstNameColumn.setVisible(isDefaultVisible);
            return firstNameColumn;
        }

        protected static TableColumn<TeacherListModel, String> getLastNameColumn(final boolean isDefaultVisible) {
            TableColumn<TeacherListModel, String> lastNameColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.LAST_NAME_COLUMN_KEY.value()));
            lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastname());
            lastNameColumn.setVisible(isDefaultVisible);
            return lastNameColumn;
        }

        protected static TableColumn<TeacherListModel, String> getAddressColumn(final boolean isDefaultVisible) {
            TableColumn<TeacherListModel, String> addressColumn = new TableColumn<>(ResourceUtil.getMessage(ADDRESS_COLUMN_KEY.value()));
            addressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress());
            addressColumn.setVisible(isDefaultVisible);
            return addressColumn;
        }

        protected static TableColumn<TeacherListModel, String> getPostalCodeColumn(final boolean isDefaultVisible) {
            TableColumn<TeacherListModel, String> postalCodeColumn = new TableColumn<>(ResourceUtil.getMessage(POSTAL_CODE_COLUMN_KEY.value()));
            postalCodeColumn.setCellValueFactory(cellData -> cellData.getValue().getPostalCode());
            postalCodeColumn.setVisible(isDefaultVisible);
            return postalCodeColumn;
        }

        protected static TableColumn<TeacherListModel, String> getCityColumn(final boolean isDefaultVisible) {
            TableColumn<TeacherListModel, String> cityColumn = new TableColumn<>(ResourceUtil.getMessage(CITY_COLUMN_KEY.value()));
            cityColumn.setCellValueFactory(cellData -> cellData.getValue().getCity());
            cityColumn.setVisible(isDefaultVisible);
            return cityColumn;
        }

        protected static TableColumn<TeacherListModel, String> getPhoneNumberColumn(final boolean isDefaultVisible) {
            TableColumn<TeacherListModel, String> phoneNumberColumn = new TableColumn<>(ResourceUtil.getMessage(PHONE_COLUMN_KEY.value()));
            phoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getPhoneNumber());
            phoneNumberColumn.setVisible(isDefaultVisible);
            return phoneNumberColumn;
        }

        protected static TableColumn<TeacherListModel, String> getPeselColumn(final boolean isDefaultVisible) {
            TableColumn<TeacherListModel, String> peselColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.PESEL_COLUMN_KEY.value()));
            peselColumn.setCellValueFactory(cellData -> cellData.getValue().getPesel());
            peselColumn.setVisible(isDefaultVisible);
            return peselColumn;
        }

        protected static TableColumn<TeacherListModel, String> getSchoolColumn(final boolean isDefaultVisible) {
            TableColumn<TeacherListModel, String> schoolColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.SCHOOL_COLUMN_KEY.value()));
            schoolColumn.setCellValueFactory(cellData -> cellData.getValue().getSchool());
            schoolColumn.setVisible(isDefaultVisible);
            return schoolColumn;
        }

        protected static TableColumn<TeacherListModel, String> getEmailColumn(boolean isDefaultVisible) {
            TableColumn<TeacherListModel, String> emailColumn = new TableColumn<>(ResourceUtil.getMessage(EMAIL_COLUMN_KEY.value()));
            emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmail());
            emailColumn.setVisible(isDefaultVisible);
            return emailColumn;
        }

    }
}
