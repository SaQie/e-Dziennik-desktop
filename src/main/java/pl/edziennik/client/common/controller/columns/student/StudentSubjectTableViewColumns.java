package pl.edziennik.client.common.controller.columns.student;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.model.student.StudentSubjectModel;
import pl.edziennik.client.common.model.student.StudentSpecificSubjectGradeModel;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.List;

import static pl.edziennik.client.common.constants.ResourceConst.*;

class StudentSubjectTableViewColumns {

    private StudentSubjectTableViewColumns() {

    }

    protected static TableColumn<StudentSubjectModel, Number> getSubjectIdentifierColumn(final boolean isDefaultVisible) {
        TableColumn<StudentSubjectModel, Number> subjectIdentifierColumn = new TableColumn<>(ResourceUtil.getMessage(IDENTIFIER_COLUMN_KEY.value()));
        subjectIdentifierColumn.setCellValueFactory(cellData -> cellData.getValue().getSubjectId());
        subjectIdentifierColumn.setVisible(isDefaultVisible);
        return subjectIdentifierColumn;
    }

    protected static TableColumn<StudentSubjectModel, String> getSubjectNameColumn(final boolean isDefaultVisible) {
        TableColumn<StudentSubjectModel, String> subjectNameColumn = new TableColumn<>(ResourceUtil.getMessage(SUBJECT_NAME_COLUMN_KEY.value()));
        subjectNameColumn.setCellValueFactory(cellData -> cellData.getValue().getSubjectName());
        subjectNameColumn.setVisible(isDefaultVisible);
        return subjectNameColumn;
    }

    protected static TableColumn<StudentSubjectModel, List<StudentSpecificSubjectGradeModel>> getSubjectGradesColumn(final boolean isDefaultVisible) {
        TableColumn<StudentSubjectModel, List<StudentSpecificSubjectGradeModel>> subjectGradesColumn = new TableColumn<>(ResourceUtil.getMessage(SUBJECT_GRADES_COLUMN_KEY.value()));
        subjectGradesColumn.setCellValueFactory(cellData -> {
            SimpleListProperty<StudentSpecificSubjectGradeModel> subjectGrades = cellData.getValue().getGrades();
            return Bindings.createObjectBinding(subjectGrades::get, subjectGrades);
        });

        Tooltip tooltip = new Tooltip();
        tooltip.setAutoHide(true);
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setHideDelay(Duration.ZERO);

        subjectGradesColumn.setCellFactory(column -> new TableCell<>() {
            // create tooltip on grades column
            @Override
            protected void updateItem(List<StudentSpecificSubjectGradeModel> item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.toString());
            }

            {

                setOnMouseClicked(event -> {
                    if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                        List<StudentSpecificSubjectGradeModel> grades = getItem();
                        if (grades != null && !grades.isEmpty()) {
                            createGradeTooltip(grades, tooltip);
                            tooltip.show(getScene().getWindow(), event.getScreenX(), event.getScreenY());
                        }
                    } else {
                        tooltip.hide();
                    }

                });
            }
        });
        subjectGradesColumn.setVisible(isDefaultVisible);
        return subjectGradesColumn;

    }


    private static void createGradeTooltip(List<StudentSpecificSubjectGradeModel> grades, Tooltip tooltip) {
        StringBuilder stringBuilder = new StringBuilder();

        String gradeText = ResourceUtil.getMessage(ResourceConst.GRADE_TOOLTIP_TEXT.value());
        String teacherText = ResourceUtil.getMessage(ResourceConst.TEACHER_TOOLTIP_TEXT.value());
        String dateText = ResourceUtil.getMessage(ResourceConst.GRADE_DATE_TOOLTIP_TEXT.value());

        Label label = new Label();
        for (StudentSpecificSubjectGradeModel grade : grades) {
            stringBuilder.append(String.format(gradeText + ": %d, " + dateText + " %s, " + teacherText + ": %s \n",
                    grade.getGrade().getValue(),
                    grade.getCreatedDate().getValue(),
                    grade.getTeacherName().getValue()));
        }

        label.setText(stringBuilder.toString());
        tooltip.setText(label.getText());
    }

}
