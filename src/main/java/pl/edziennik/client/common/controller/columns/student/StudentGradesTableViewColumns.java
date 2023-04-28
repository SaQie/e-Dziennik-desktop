package pl.edziennik.client.common.controller.columns.student;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import pl.edziennik.client.common.model.admin.AdminListModel;
import pl.edziennik.client.common.model.student.StudentGradeModel;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.List;

import static pl.edziennik.client.common.constants.ResourceConst.*;

class StudentGradesTableViewColumns {

    private StudentGradesTableViewColumns() {

    }

    protected static TableColumn<StudentGradeModel, Number> getSubjectIdentifierColumn(final boolean isDefaultVisible) {
        TableColumn<StudentGradeModel, Number> subjectIdentifierColumn = new TableColumn<>(ResourceUtil.getMessage(IDENTIFIER_COLUMN_KEY.value()));
        subjectIdentifierColumn.setCellValueFactory(cellData -> cellData.getValue().getSubjectId());
        subjectIdentifierColumn.setVisible(isDefaultVisible);
        return subjectIdentifierColumn;
    }

    protected static TableColumn<StudentGradeModel, String> getSubjectNameColumn(final boolean isDefaultVisible) {
        TableColumn<StudentGradeModel, String> subjectNameColumn = new TableColumn<>(ResourceUtil.getMessage(SUBJECT_NAME_COLUMN_KEY.value()));
        subjectNameColumn.setCellValueFactory(cellData -> cellData.getValue().getSubjectName());
        subjectNameColumn.setVisible(isDefaultVisible);
        return subjectNameColumn;
    }

    protected static TableColumn<StudentGradeModel, List<Integer>> getSubjectGradesColumn(final boolean isDefaultVisible) {
        TableColumn<StudentGradeModel, List<Integer>> subjectGradesColumn = new TableColumn<>(ResourceUtil.getMessage(SUBJECT_NAME_COLUMN_KEY.value()));
        subjectGradesColumn.setCellValueFactory(cellData -> {
            SimpleListProperty<Integer> subjectGrades = cellData.getValue().getSubjectGrades();
            return Bindings.createObjectBinding(subjectGrades::get, subjectGrades);
        });
        subjectGradesColumn.setVisible(isDefaultVisible);
        return subjectGradesColumn;
    }

}
