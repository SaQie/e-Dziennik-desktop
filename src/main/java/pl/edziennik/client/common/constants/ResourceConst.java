package pl.edziennik.client.common.constants;

public enum ResourceConst {

    // PATHS

    ALERT_STYLES_ADDRESS("/pl/edziennik/client/css/alert-styles.css"),
    TABLE_STYLES_ADDRESS("/pl/edziennik/client/css/global-table-styles.css"),
    GLOBAL_COLOR_STYLES("/pl/edziennik/client/css/global-colors.css"),
    DICTIONARY_STYLES_ADDRESS("/pl/edziennik/client/css/dictionary-styles.css"),
    POPUP_STYLES_ADDRESS("/pl/edziennik/client/css/popup-styles.css"),
    INFORMATION_ICON_ADDRESS("/pl/edziennik/client/icons/Information.png"),
    ADD_GRADE_ACTION_ICON("/pl/edziennik/client/icons/Add Subnode.png"),
    ADD_GRADES_ACTION_ICON("/pl/edziennik/client/icons/Add Node.png"),
    MARK_ICON_ADDRESS("/pl/edziennik/client/icons/Easy.png"),
    WARNING_ICON_ADDRESS("/pl/edziennik/client/icons/Warning.png"),
    CLEAR_ICON_ADDRESS("/pl/edziennik/client/icons/Close.png"),
    MESSAGES_RESOURCES_ADDRESS("pl.edziennik.client.bundles.messages"),
    ERROR_ICON_ADDRESS("/pl/edziennik/client/icons/error.png"),
    SUCCESS_ICON_ADDRESS("/pl/edziennik/client/icons/success.png"),
    APPLICATION_PROPERTIES_ADDRESS("/pl/edziennik/client/application.properties"),
    APPLICATION_PROPERTIES_STREAM_ADDRESS("/pl/edziennik/client/application.properties"),

    // VIEWS

    DASHBOARD_STUDENT_VIEW_ADDRESS("/pl/edziennik/client/view/student/student-dashboard-view.fxml"),
    DASHBOARD_TEACHER_VIEW_ADDRESS("/pl/edziennik/client/view/teacher/teacher-dashboard-view.fxml"),
    TEACHER_GRADE_MANAGMENT_VIEW_ADDRESS("/pl/edziennik/client/view/teacher/grade/teacher-grades-managment-tab.fxml"),
    DASHBOARD_STUDENT_SPECIFIC_SUBJECT_GRADES_VIEW_ADDRESS("/pl/edziennik/client/view/student/grade/student-dashboard-show-grades-option.fxml"),
    DASHBOARD_ADMIN_VIEW_ADDRESS("/pl/edziennik/client/view/admin/admin-dashboard-view.fxml"),
    DASHBOARD_ADMIN_SCHOOL_ADD_VIEW_ADDRESS("/pl/edziennik/client/view/admin/school/admin-dashboard-schools-tab-add.fxml"),
    DASHBOARD_ADMIN_SCHOOL_EDIT_VIEW_ADDRESS("/pl/edziennik/client/view/admin/school/admin-dashboard-schools-tab-edit.fxml"),
    DASHBOARD_ADMIN_SCHOOL_SHOW_VIEW_ADDRESS("/pl/edziennik/client/view/admin/school/admin-dashboard-schools-tab-show.fxml"),
    DASHBOARD_ADMIN_SCHOOL_VIEW_ADDRESS("/pl/edziennik/client/view/admin/school/admin-dashboard-schools-tab.fxml"),
    DASHBOARD_ADMIN_SCHOOL_CLASS_ADD_ADDRESS("/pl/edziennik/client/view/admin/schoolclasses/admin-dashboard-schoolclasses-tab-add.fxml"),
    DASHBOARD_ADMIN_SCHOOL_CLASS_EDIT_ADDRESS("/pl/edziennik/client/view/admin/schoolclasses/admin-dashboard-schoolclasses-tab-edit.fxml"),
    DASHBOARD_ADMIN_SCHOOL_CLASS_SHOW_ADDRESS("/pl/edziennik/client/view/admin/schoolclasses/admin-dashboard-schoolclasses-tab-show.fxml"),
    PROGRESS_BAR_LITTLE_VIEW_ADDRESS("/pl/edziennik/client/view/popup/waiting-pop-up-little.fxml"),
    PROGRESS_BAR_LARGE_VIEW_ADDRESS("/pl/edziennik/client/view/popup/waiting-pop-up.fxml"),
    TABLE_COLUMN_VIEW_CONFIG_VIEW_ADDRESS("/pl/edziennik/client/view/config/table-columns-view-configuration.fxml"),
    AUTHORIZATION_VIEW_ADDRESS("/pl/edziennik/client/view/auth/authorization-view.fxml"),
    DASHBOARD_ADMIN_CONFIGURATION_VIEW_ADDRESS("/pl/edziennik/client/view/admin/configuration/admin-dashboard-configuration-option-view.fxml"),
    DASHBOARD_ADMIN_ACCOUNTS_ADD_STUDENT_VIEW_ADDRESS("/pl/edziennik/client/view/admin/account/student/admin-dashboard-accounts-students-add.fxml"),
    DASHBOARD_ADMIN_ACCOUNTS_EDIT_STUDENT_VIEW_ADDRESS("/pl/edziennik/client/view/admin/account/student/admin-dashboard-accounts-students-edit.fxml"),
    DASHBOARD_ADMIN_ACCOUNTS_SHOW_STUDENT_VIEW_ADDRESS("/pl/edziennik/client/view/admin/account/student/admin-dashboard-accounts-students-show.fxml"),
    DASHBOARD_ADMIN_ACCOUNTS_ADD_TEACHER_VIEW_ADDRESS("/pl/edziennik/client/view/admin/account/teacher/admin-dashboard-accounts-teachers-add.fxml"),
    DASHBOARD_ADMIN_ACCOUNTS_EDIT_TEACHER_VIEW_ADDRESS("/pl/edziennik/client/view/admin/account/teacher/admin-dashboard-accounts-teachers-edit.fxml"),
    DASHBOARD_ADMIN_ACCOUNTS_SHOW_TEACHER_VIEW_ADDRESS("/pl/edziennik/client/view/admin/account/teacher/admin-dashboard-accounts-teachers-show.fxml"),
    DASHBOARD_ADMIN_ACCOUNTS_ADD_ADMIN_VIEW_ADDRESS("/pl/edziennik/client/view/admin/account/administration/admin-dashboard-accounts-administration-add.fxml"),
    DASHBOARD_ADMIN_ACCOUNTS_EDIT_ADMIN_VIEW_ADDRESS("/pl/edziennik/client/view/admin/account/administration/admin-dashboard-accounts-administration-edit.fxml"),
    DASHBOARD_ADMIN_ACCOUNTS_SHOW_ADMIN_VIEW_ADDRESS("/pl/edziennik/client/view/admin/account/administration/admin-dashboard-accounts-administration-show.fxml"),
    DASHBOARD_ADMIN_ACCOUNTS_ADD_PARENT_VIEW_ADDRESS("/pl/edziennik/client/view/admin/account/parent/admin-dashboard-accounts-parents-add.fxml"),
    DASHBOARD_ADMIN_ACCOUNTS_EDIT_PARENT_VIEW_ADDRESS("/pl/edziennik/client/view/admin/account/parent/admin-dashboard-accounts-parents-edit.fxml"),
    DASHBOARD_ADMIN_ACCOUNTS_SHOW_PARENT_VIEW_ADDRESS("/pl/edziennik/client/view/admin/account/parent/admin-dashboard-accounts-parents-show.fxml"),
    DASHBOARD_ADMIN_CONFIGURATION_VALUE_VIEW_ADDRESS("/pl/edziennik/client/view/admin/configuration/admin-dashboard-configuration-value-view.fxml"),
    REGISTER_VIEW_ADDRESS("/pl/edziennik/client/view/auth/register-student-view.fxml"),
    // MESSAGES,

    ADD_GRADE_ACTION_NAME("add.grade.action"),
    ADD_GRADES_ACTION_NAME("add.grades.action"),
    TEACHER_GRADE_MANAGMENT_VIEW_TITLE("teacher.grade.managment.view.title"),
    EXIT_CONFIRMATION_MESSAGE_KEY("exit.confirmation.message"),
    DASHBOARD_ADMIN_VIEW_TITLE("dashboard.admin.view.title"),
    DASHBOARD_TEACHER_VIEW_TITLE("dashboard.teacher.view.title"),
    DASHBOARD_STUDENT_VIEW_TITLE("dashboard.student.view.title"),
    EXIT_CONFIRMATION_HEADER_KEY("exit.confirmation.header"),
    ERROR_DIALOG_MESSAGE_KEY("error.dialog.message"),
    ERROR_DIALOG_HEADER_MESSAGE_KEY("error.dialog.header"),
    SUCCESS_DIALOG_HEADER_MESSAGE_KEY("success.dialog.header"),
    SUCCESS_DIALOG_CONTENT_MESSAGE_KEY("success.dialog.content"),
    SUCCESS_DIALOG_TITLE_MESSAGE_KEY("success.dialog.title"),
    SHOW_GRADES_TITLE_MESSAGE_KEY("show.grades.title"),
    COLUMN_VIEW_CONFIGURATION_TITLE_MESSAGE_KEY("configuration.window.title"),
    LOGOUT_DIALOG_TITLE_MESSAGE_KEY("logout.dialog.title"),
    EDIT_STUDENT_VIEW_TITLE_KEY("edit.student.view.title"),
    EDIT_TEACHER_VIEW_TITLE_KEY("edit.teacher.view.title"),
    LOGOUT_DIALOG_HEADER_MESSAGE_KEY("logout.dialog.header"),
    REGISTER_VIEW_TITLE("register.view.title"),
    BUTTON_YES_KEY("button.yes"),
    ADD_PARENT_TITLE_MESSAGE_KEY("add.parent.title"),
    EDIT_PARENT_TITLE_MESSAGE_KEY("edit.parent.title"),
    SHOW_PARENT_TITLE_MESSAGE_KEY("show.parent.title"),
    LOGIN_SUCCESSFULL("login.succesfull"),
    BUTTON_NO_KEY("button.no"),
    SHOW_TEACHER_VIEW_TITLE("label.show.teacher"),
    BUTTON_OK_KEY("button.ok"),
    BUTTON_CANCEL_KEY("button.cancel"),
    BUTTON_SAVE_KEY("button.save"),
    LOGOUT_SUCCESSFULL("logout.successfull"),
    ADMIN_ACCOUNTS_ADD_TEACHER_TITLE_MESSAGE_KEY("add.teacher.title"),
    ADMIN_ACCOUNTS_ADD_ADMIN_TITLE_MESSAGE_KEY("add.admin.title"),
    ADMIN_ACCOUNTS_EDIT_ADMIN_TITLE_MESSAGE_KEY("edit.admin.title"),
    ADMIN_ACCOUNTS_SHOW_ADMIN_TITLE_MESSAGE_KEY("show.admin.title"),
    WAITING_REGISTER_MESSAGE_KEY("waiting.register.message"),
    WAITING_FETCH_DATA("waiting.fetch.data"),
    WAITING_SAVE_DATA("waiting.save.data"),
    WAITING_DELETE_DATA("waiting.delete.data"),
    NAME_COLUMN_KEY("name.column.text"),
    ADDRESS_COLUMN_KEY("address.column.text"),
    PHONE_COLUMN_KEY("phone.number.column.text"),
    REGON_COLUMN_KEY("regon.column.text"),
    NIP_COLUMN_KEY("nip.column.text"),
    SUPERVISING_TEACHER_COLUMN_KEY("supervisingteacher.column.text"),
    USERNAME_COLUMN_KEY("username.column.text"),
    EMAIL_COLUMN_KEY("email.column.text"),
    ROLE_COLUMN_KEY("role.column.text"),
    FIELD_CANNOT_BE_EMPTY("field.empty"),
    STUDENT_NAME_COLUMN_KEY("student.name.column.text"),
    FIRST_NAME_COLUMN_KEY("first.name.column.text"),
    LAST_NAME_COLUMN_KEY("last.name.column.text"),
    SCHOOL_COLUMN_KEY("school.column.text"),
    SCHOOL_CLASS_COLUMN_KEY("school.class.column.text"),
    TEACHER_COLUMN_KEY("teacher.column.text"),
    PARENT_FULL_NAME_COLUMN_KEY("parent.fullname.column.text"),
    PESEL_COLUMN_KEY("pesel.column.text"),
    PARENT_FIRST_NAME_COLUMN_KEY("parent.first.name.column.text"),
    PARENT_LAST_NAME_COLUMN_KEY("parent.last.name.column.text"),
    CITY_COLUMN_KEY("city.column.text"),
    IDENTIFIER_COLUMN_KEY("identifier.column.text"),
    GRADE_COLUMN_KEY("grade.column.text"),
    COMMENT_TEXT("comment.text"),
    WEIGHT_COLUMN_KEY("weight.column.text"),
    DESCRIPTION_COLUMN_KEY("description.column.text"),
    CREATED_DATE_COLUMN_KEY("created.date.column.text"),
    POSTED_BY_COLUMN_KEY("posted.by.column.text"),
    SUBJECT_NAME_COLUMN_KEY("subject.name.column.text"),
    ACTIONS_COLUMN_KEY("column.actions.text"),
    SUBJECT_IDENTIFIER_COLUMN_KEY("subject.identifier.column.text"),
    SUBJECT_GRADES_COLUMN_KEY("subject.grades.column.text"),
    POSTAL_CODE_COLUMN_KEY("postal.code.column.text"),
    SCHOOL_LEVEL_NAME_COLUMN_KEY("school.level.column.text"),
    SUCCESS_DIALOG_SAVE_CONFIGURATION_MESSAGE("success.dialog.configuration.message"),
    HIGH_SCHOOL_NAME_KEY("label.high.school"),
    UNIVERSITY_SCHOOL_NAME_KEY("label.university.school"),
    PRIMARY_SCHOOL_NAME_KEY("label.primary.school"),
    SHOW_STUDENT_VIEW_TITLE_KEY("show.student.title.message"),
    TABLE_VIEW_PLACEHOLDER_MESSAGE_KEY("label.table.empty"),
    SAVING_NEW_SCHOOL_MESSAGE_KEY("save.new.school.progress.message"),
    ADD_SCHOOL_VIEW_TITLE_KEY("add.school.view.title.label"),
    ADD_SCHOOL_CLASS_VIEW_TITLE_KEY("add.school.class.view.title.label"),
    EDIT_SCHOOL_CLASS_VIEW_TITLE_KEY("edit.school.class.view.title.label"),
    SHOW_SCHOOL_CLASS_VIEW_TITLE_KEY("show.school.class.view.title.label"),
    EDIT_SCHOOL_VIEW_TITLE_KEY("edit.school.view.title.label"),
    GRADE_TOOLTIP_TEXT("grade.tooltip.text"),
    WEIGHT_TOOLTIP_TEXT("weight.tooltip.text"),
    DESCRIPTION_TOOLTIP_TEXT("description.tooltip.text"),
    TEACHER_TOOLTIP_TEXT("teacher.tooltip.text"),
    GRADE_DATE_TOOLTIP_TEXT("grade.date.tooltip.text"),
    SHOW_SCHOOL_VIEW_TITLE_KEY("show.school.view.title.label"),
    WAITING_DELETE_SCHOOL_MESSAGE_KEY("waiting.delete.school.message"),
    AUTHORIZATION_VIEW_TITLE_MESSAGE_KEY("authorization.title.message"),
    CONFIGURATION_LIST_ADMIN_VIEW_TITLE_MESSAGE_KEY("show.admin.configuration.title.label"),
    ADMIN_ACCOUNTS_ADD_STUDENT_TITLE_MESSAGE_KEY("admin.accounts.student.add.title"),
    HINT_INSERT_SCHOOL_IDENTIFIER_CONFIGURATION("hint.insert.school.identifier"),
    QUESTION_ASSIGN_SCHOOL_AS_DEFAULT_TO_REGISTER("question.assign.school.as.default.to.register"),

    // ERROR CODES

    USERNAME_FIELD_IS_EMPTY_KEY("field.username.empty"),
    EMAIL_FIELD_IS_EMPTY_KEY("field.email.empty"),
    PASSWORD_FIELD_IS_EMPTY_KEY("field.password.empty"),
    PASSWORD_REPEAT_FIELD_IS_EMPTY_KEY("field.password.repeat.empty"),
    USERNAME_FIELD_LENGTH_LIMIT_KEY("username.field.length.limit"),
    PASSWORD_FIELD_LENGTH_LIMIT_KEY("password.field.length.limit"),
    EMAIL_NOT_VALID_KEY("email.field.not.valid"),
    PASSWORD_NOT_VALID_KEY("password.field.not.valid"),
    PASSWORD_AND_REPEAT_PASSWORD_NOT_EQUALS_KEY("password.field.not.equals.to.repeat.password"),
    PARSE_ERROR("restclient.parese.error"),
    SERVER_ERROR_MESSAGE_KEY("restclient.server.error"),
    SERVER_NOT_RESPONDING_MESSAGE_KEY("restclient.server.not.respond"),
    UNAUTHORIZED_ERROR_MESSAGE_KEY("restclient.unauthorized.message"),
    ACCESS_DENIED_ERROR_MESSAGE_KEY("restclient.access.denied.message"),
    NAME_FIELD_IS_EMPTY_KEY("field.name.empty"),
    STUDENT_CANNOT_BE_EMPTY("combobox.student.item.empty"),
    ADDRESS_FIELD_IS_EMPTY_KEY("field.address.empty"),
    POSTAL_CODE_IS_EMPTY_KEY("field.postal.code.empty"),
    POSTAL_CODE_NOT_VALID_KEY("field.postal.code.not.valid"),
    CITY_FIELD_IS_EMPTY_KEY("field.city.empty"),
    NIP_FIELD_IS_EMPTY_KEY("field.nip.empty"),
    NIP_CODE_NOT_VALID_KEY("field.nip.not.valid"),
    REGON_FIELD_IS_EMPTY_KEY("field.regon.empty"),
    REGON_CODE_NOT_VALID_KEY("field.regon.not.valid"),
    PHONE_NUMBER_FIELD_IS_EMPTY_KEY("field.phone.number.empty"),
    PHONE_NUMBER_CODE_NOT_VALID_KEY("field.phone.number.not.valid"),
    TASK_ERROR_MESSAGE_KEY("task.execute.error"),
    UNCAUGHT_EXCEPTION_MESSAGE_KEY("uncaught.exception.message.key"),
    TABLE_VIEW_ROW_NOT_SELECTED_MESSAGE_KEY("rows.not.selected"),
    ERROR_DIALOG_TITLE_MESSAGE_KEY("dialog.error.title"),
    TABLE_VIEW_TOO_MANY_ROWS_MESSAGE_KEY("too.many.rows.selected"),
    ARE_YOU_SURE_TO_PERFORM_THIS_OPERATION_MESSAGE_KEY("are.you.sure.to.perform.this.operation"),
    DIALOG_QUESTION_MESSAGE_KEY("dialog.perform.operation.question"),
    CHOICE_DIALOG_TITLE_KEY("choice.dialog.title"),
    DICTIONARY_TITLE_KEY("dictionary.dialog.title"),
    BUTTON_SELECT_KEY("button.select"),
    ARE_YOU_SURE_TO_PERFORM_DELETE_OPERATION_MESSAGE_KEY("are.you.sure.to.perform.delete.action"),
    VIEW_EXCEPTION_MESSAGE_KEY("view.exception.message.key"),
    FIRST_NAME_FIELD_IS_EMPTY_KEY("first.name.field.is.empty"),
    LAST_NAME_FIELD_IS_EMPTY_KEY("last.name.field.is.empty"),
    PESEL_FIELD_IS_NOT_CORRECT("pesel.field.is.not.correct"),
    SCHOOL_CANNOT_BE_EMPTY("school.cannot.be.empty"),
    SCHOOL_CLASS_CANNOT_BE_EMPTY("school.class.cannot.be.empty"),
    CANNOT_DELETE_YOUR_OWN_ACCOUNT_MESSAGE_KEY("cannot.delete.own.account"),

    // LOCALES

    LOCALE_EN_STRING("en-EN"),
    LOCALE_PL_STRING("pl-PL"),

    // PROPERTIES

    PROPERTIES_LOADER_ROLE_KEY("role"),
    PROPERTIES_LOADER_ID_KEY("id"),
    PROPERTIES_LOADER_SUPER_ID_KEY("superId"),
    PROPERTIES_LOADER_NAME_KEY("name"),
    PROPERTIES_LOADER_TOKEN_KEY("token"),
    PROPERTIES_LOADER_REFRESH_TOKEN_KEY("refreshToken"),
    PROPERTIES_LOADER_SERVER_ADDRESS_KEY("serverAddress");


    private final String resource;

    ResourceConst(String resource) {
        this.resource = resource;
    }

    public String value() {
        return resource;
    }

    @Override
    public String toString() {
        return resource;
    }
}
