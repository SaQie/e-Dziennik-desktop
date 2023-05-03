package pl.edziennik.client.common.controller.column.teacher;

import javafx.scene.control.TableColumn;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.model.teacher.TeacherSubjectsModel;
import pl.edziennik.client.util.ResourceUtil;

class TeacherSubjectsTableViewColumns {

    private TeacherSubjectsTableViewColumns() {

    }

    protected static TableColumn<TeacherSubjectsModel, String> getSubjectNameColumn(final boolean isDefaultVisible) {
        TableColumn<TeacherSubjectsModel, String> subjectNameColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.SUBJECT_NAME_COLUMN_KEY.value()));
        subjectNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        subjectNameColumn.setVisible(isDefaultVisible);
        return subjectNameColumn;
    }

    protected static TableColumn<TeacherSubjectsModel, String> getDescriptionColumn(final boolean isDefaultVisible) {
        TableColumn<TeacherSubjectsModel, String> descriptionColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.DESCRIPTION_COLUMN_KEY.value()));
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());
        descriptionColumn.setVisible(isDefaultVisible);
        return descriptionColumn;
    }

    protected static TableColumn<TeacherSubjectsModel, String> getSchoolClassNameColumn(final boolean isDefaultVisible) {
        TableColumn<TeacherSubjectsModel, String> schoolClassNameColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.SCHOOL_CLASS_COLUMN_KEY.value()));
        schoolClassNameColumn.setCellValueFactory(cellData -> cellData.getValue().getSchoolClassName());
        schoolClassNameColumn.setVisible(isDefaultVisible);
        return schoolClassNameColumn;
    }

    protected static TableColumn<TeacherSubjectsModel, String> getTeacherNameColumn(final boolean isDefaultVisible) {
        TableColumn<TeacherSubjectsModel, String> teacherNameColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.TEACHER_COLUMN_KEY.value()));
        teacherNameColumn.setCellValueFactory(cellData -> cellData.getValue().getTeacherName());
        teacherNameColumn.setVisible(isDefaultVisible);
        return teacherNameColumn;
    }





}
