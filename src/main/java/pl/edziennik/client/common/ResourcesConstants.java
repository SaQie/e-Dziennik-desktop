package pl.edziennik.client.common;

import java.util.Locale;

public class ResourcesConstants {

    public ResourcesConstants() {
    }

    /*
        PATHS
     */

    public static final String ALERT_STYLES_ADDRESS = "/pl/edziennik/client/css/alert-styles.css";
    public static final String INFORMATION_ICON_ADDRESS = "/pl/edziennik/client/icons/Information.png";
    public static final String MESSAGES_RESOURCES_ADDRESS = "pl.edziennik.client.bundles.messages";
    public static final String ERROR_ICON_ADDRESS = "/pl/edziennik/client/icons/error.png";
    public static final String SUCCESS_ICON_ADDRESS = "/pl/edziennik/client/icons/success.png";

    public static final String DASHBOARD_ADMIN_VIEW_ADDRESS = "/pl/edziennik/client/admin-dashboard-view.fxml";
    public static final String DASHBOARD_ADMIN_SCHOOL_TAB_VIEW_ADDRESS = "/pl/edziennik/client/admin-dashboard-schools-tab.fxml";
    public static final String DASHBOARD_ADMIN_SCHOOL_ADD_VIEW_ADDRESS = "/pl/edziennik/client/admin-dashboard-schools-tab-add.fxml";


    /*
        MESSAGES CONSTS
     */

    public static final String EXIT_CONFIRMATION_MESSAGE_KEY = "exit.confirmation.message";
    public static final String EXIT_CONFIRMATION_HEADER_KEY = "exit.confirmation.header";
    public static final String ERROR_DIALOG_MESSAGE_KEY = "error.dialog.message";
    public static final String ERROR_DIALOG_HEADER_MESSAGE_KEY = "error.dialog.header";
    public static final String SUCCESS_DIALOG_HEADER_MESSAGE_KEY = "success.dialog.header";
    public static final String SUCCESS_DIALOG_CONTENT_MESSAGE_KEY = "success.dialog.content";
    public static final String SUCCESS_DIALOG_TITLE_MESSAGE_KEY = "success.dialog.title";
    public static final String LOGOUT_DIALOG_TITLE_MESSAGE_KEY = "logout.dialog.title";
    public static final String LOGOUT_DIALOG_HEADER_MESSAGE_KEY = "logout.dialog.header";
    public static final String BUTTON_YES_KEY = "button.yes";
    public static final String BUTTON_NO_KEY = "button.no";
    public static final String BUTTON_OK_KEY = "button.ok";
    public static final String WAITING_REGISTER_MESSAGE_KEY = "waiting.register.message";
    public static final String FETCHING_SCHOOL_LIST_DATA_MESSAGE_KEY = "fetch.school.list.data.message";
    public static final String NAME_COLUMN_KEY = "name.column.text";
    public static final String ADDRESS_COLUMN_KEY = "address.column.text";
    public static final String PHONE_COLUMN_KEY = "phone.number.column.text";
    public static final String REGON_COLUMN_KEY = "regon.column.text";
    public static final String NIP_COLUMN_KEY = "nip.column.text";
    public static final String CITY_COLUMN_KEY = "city.column.text";
    public static final String POSTAL_CODE_COLUMN_KEY = "postal.code.column.text";
    public static final String SCHOOL_LEVEL_NAME_COLUMN_KEY = "school.level.column.text";
    public static final String SUCCESS_DIALOG_SAVE_CONFIGURATION_MESSAGE = "success.dialog.configuration.message";
    public static final String FETCHING_SCHOOL_LEVEL_LIST_MESSAGE_KEY = "fetch.school.level.list";
    public static final String HIGH_SCHOOL_NAME_KEY = "label.high.school";
    public static final String UNIVERSITY_SCHOOL_NAME_KEY = "label.university.school";
    public static final String PRIMARY_SCHOOL_NAME_KEY = "label.primary.school";
    public static final String TABLE_VIEW_PLACEHOLDER_MESSAGE_KEY = "label.table.empty";
    public static final String SAVING_NEW_SCHOOL_MESSAGE_KEY = "save.new.school.progress.message";

    // ERROR CODES

    public static final String USERNAME_FIELD_IS_EMPTY_KEY = "field.username.empty";
    public static final String EMAIL_FIELD_IS_EMPTY_KEY = "field.email.empty";
    public static final String PASSWORD_FIELD_IS_EMPTY_KEY = "field.password.empty";
    public static final String PASSWORD_REPEAT_FIELD_IS_EMPTY_KEY = "field.password.repeat.empty";
    public static final String USERNAME_FIELD_LENGTH_LIMIT_KEY = "username.field.length.limit";
    public static final String PASSWORD_FIELD_LENGTH_LIMIT_KEY = "password.field.length.limit";
    public static final String EMAIL_NOT_VALID_KEY = "email.field.not.valid";
    public static final String PASSWORD_NOT_VALID_KEY = "password.field.not.valid";
    public static final String PASSWORD_AND_REPEAT_PASSWORD_NOT_EQUALS_KEY = "password.field.not.equals.to.repeat.password";
    public static final String PARSE_ERROR = "restclient.parese.error";
    public static final String SERVER_ERROR_MESSAGE_KEY = "restclient.server.error";
    public static final String SERVER_NOT_RESPONDING_MESSAGE_KEY = "restclient.server.not.respond";
    public static final String UNAUTHORIZED_ERROR_MESSAGE_KEY = "restclient.unauthorized.message";
    public static final String ACCESS_DENIED_ERROR_MESSAGE_KEY = "restclient.access.denied.message";
    public static final String NAME_FIELD_IS_EMPTY_KEY = "field.name.empty";
    public static final String ADDRESS_FIELD_IS_EMPTY_KEY = "field.address.empty";
    public static final String POSTAL_CODE_IS_EMPTY_KEY = "field.postal.code.empty";
    public static final String POSTAL_CODE_NOT_VALID_KEY = "field.postal.code.not.valid";
    public static final String CITY_FIELD_IS_EMPTY_KEY = "field.city.empty";
    public static final String NIP_FIELD_IS_EMPTY_KEY = "field.nip.empty";
    public static final String NIP_CODE_NOT_VALID_KEY = "field.nip.not.valid";
    public static final String REGON_FIELD_IS_EMPTY_KEY = "field.regon.empty";
    public static final String REGON_CODE_NOT_VALID_KEY = "field.regon.not.valid";
    public static final String PHONE_NUMBER_FIELD_IS_EMPTY_KEY = "field.phone.number.empty";
    public static final String PHONE_NUMBER_CODE_NOT_VALID_KEY = "field.phone.number.not.valid";

    // LOCALES

    public static final Locale LOCALE_EN = new Locale("en", "EN");
    public static final Locale LOCALE_PL = new Locale("pl", "PL");
    public static final String LOCALE_EN_STRING = "en-EN";
    public static final String LOCALE_PL_STRING = "pl-PL";

}
