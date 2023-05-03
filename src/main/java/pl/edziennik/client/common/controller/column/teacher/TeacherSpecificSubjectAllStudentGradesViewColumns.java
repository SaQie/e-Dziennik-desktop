package pl.edziennik.client.common.controller.column.teacher;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.stage.PopupWindow;
import javafx.stage.Window;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.model.student.StudentSpecificSubjectGradeModel;
import pl.edziennik.client.common.model.teacher.TeacherAllStudentGradesForSpecificSubjectModel;
import pl.edziennik.client.common.model.teacher.TeacherSubjectsModel;
import pl.edziennik.client.util.ResourceUtil;

import java.util.List;

class TeacherSpecificSubjectAllStudentGradesViewColumns {


    private TeacherSpecificSubjectAllStudentGradesViewColumns() {

    }

    protected static TableColumn<TeacherAllStudentGradesForSpecificSubjectModel, String> getStudentFullNameColumn(final boolean isDefaultVisible) {
        TableColumn<TeacherAllStudentGradesForSpecificSubjectModel, String> studentFullNameColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.STUDENT_NAME_COLUMN_KEY.value()));
        studentFullNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFullName());
        studentFullNameColumn.setVisible(isDefaultVisible);
        return studentFullNameColumn;
    }


    protected static TableColumn<TeacherAllStudentGradesForSpecificSubjectModel, String> getSubjectNameColumn(final boolean isDefaultVisible) {
        TableColumn<TeacherAllStudentGradesForSpecificSubjectModel, String> subjectNameColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.SUBJECT_NAME_COLUMN_KEY.value()));
        subjectNameColumn.setCellValueFactory(cellData -> cellData.getValue().getSubjectName());
        subjectNameColumn.setVisible(isDefaultVisible);
        return subjectNameColumn;
    }

    protected static TableColumn<TeacherAllStudentGradesForSpecificSubjectModel, Number> getSubjectIdentifierColumn(final boolean isDefaultVisible) {
        TableColumn<TeacherAllStudentGradesForSpecificSubjectModel, Number> subjectIdentifierColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.SUBJECT_IDENTIFIER_COLUMN_KEY.value()));
        subjectIdentifierColumn.setCellValueFactory(cellData -> cellData.getValue().getSubjectId());
        subjectIdentifierColumn.setVisible(isDefaultVisible);
        return subjectIdentifierColumn;
    }

    protected static TableColumn<TeacherAllStudentGradesForSpecificSubjectModel, List<StudentSpecificSubjectGradeModel>> getStudentGradesColumn(final boolean isDefaultVisible) {
        TableColumn<TeacherAllStudentGradesForSpecificSubjectModel, List<StudentSpecificSubjectGradeModel>> gradesColumn = new TableColumn<>(ResourceUtil.getMessage(ResourceConst.GRADE_COLUMN_KEY.value()));
        gradesColumn.setCellValueFactory(cellData -> {
            SimpleListProperty<StudentSpecificSubjectGradeModel> subjectGrades = cellData.getValue().getGrades();
            return Bindings.createObjectBinding(subjectGrades::get, subjectGrades);
        });

        gradesColumn.setCellFactory(column -> new TableCell<>() {
            // create tooltip on grades column
            @Override
            protected void updateItem(List<StudentSpecificSubjectGradeModel> item, boolean empty) {
                // set graphic column view for each grade
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                    return;
                }

                HBox hbox = new HBox();
                hbox.setSpacing(5);

                // TODO on click

                for (StudentSpecificSubjectGradeModel specificItem : item) {
                    Label gradeLabel = new Label(specificItem.toString());
                    Styles.setGradeLabelStyles(gradeLabel, specificItem.getGrade().getValue());
                    hbox.getChildren().add(gradeLabel);
                }
                setGraphic(hbox);
            }

        });

        gradesColumn.setVisible(isDefaultVisible);
        return gradesColumn;
    }




}
