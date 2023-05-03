package pl.edziennik.client.common.controller.columns.admin;

import javafx.scene.control.TableColumn;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.controller.columns.BasicTableViewColumns;
import pl.edziennik.client.common.model.admin.SchoolClassListModel;
import pl.edziennik.client.utils.ResourceUtil;

class SchoolClassTableViewColumns extends BasicTableViewColumns {

    private SchoolClassTableViewColumns(){

    }

    protected static TableColumn<SchoolClassListModel, String> getSchoolClassNameColumn(final boolean isDefaultVisible){
        TableColumn<SchoolClassListModel, String> nameColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.NAME_COLUMN_KEY.value()));
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getClassName());
        nameColumn.setVisible(isDefaultVisible);
        return nameColumn;
    }

    protected static TableColumn<SchoolClassListModel, String> getSupervisingTeacherNameColumn(final boolean isDefaultVisible){
        TableColumn<SchoolClassListModel, String> teacherNameColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.SUPERVISING_TEACHER_COLUMN_KEY.value()));
        teacherNameColumn.setCellValueFactory(cellData -> cellData.getValue().getSupervisingTeacherFullName());
        teacherNameColumn.setVisible(isDefaultVisible);
        return teacherNameColumn;
    }

    protected static TableColumn<SchoolClassListModel, String> getSchoolNameColumn(final boolean isDefaultVisible){
        TableColumn<SchoolClassListModel, String> schoolNameColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.SCHOOL_COLUMN_KEY.value()));
        schoolNameColumn.setCellValueFactory(cellData -> cellData.getValue().getSchoolName());
        schoolNameColumn.setVisible(isDefaultVisible);
        return schoolNameColumn;
    }

}
