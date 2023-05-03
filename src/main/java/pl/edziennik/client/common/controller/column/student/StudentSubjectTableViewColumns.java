package pl.edziennik.client.common.controller.column.student;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.stage.PopupWindow;
import javafx.stage.Window;
import javafx.util.Duration;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.model.student.StudentSubjectModel;
import pl.edziennik.client.common.model.student.StudentSpecificSubjectGradeModel;
import pl.edziennik.client.util.ResourceUtil;

import java.util.ArrayList;
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
        List<Tooltip> tooltips = new ArrayList<>();

        subjectGradesColumn.setCellFactory(column -> new TableCell<>() {
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

                for (StudentSpecificSubjectGradeModel specificItem : item) {
                    Label gradeLabel = new Label(specificItem.toString());

                    Tooltip gradeTooltip = createGradeTooltip(specificItem);
                    tooltips.add(gradeTooltip);

                    Styles.setGradeLabelStyles(gradeLabel, specificItem.getGrade().getValue());

                    // Open tooltip after mouse click
                    gradeLabel.setOnMouseClicked(e -> {
                        if (e.getButton().equals(MouseButton.PRIMARY)) {
                            tooltips.stream().filter(Window::isShowing).forEach(PopupWindow::hide);
                            gradeTooltip.show(gradeLabel, e.getScreenX(), e.getScreenY());
                        }
                        if (e.getButton().equals(MouseButton.SECONDARY)){
                            tooltips.stream().filter(
                                    Window::isShowing).forEach(PopupWindow::hide);
                        }
                    });

                    hbox.getChildren().add(gradeLabel);

                }
                setGraphic(hbox);
            }

        });
        subjectGradesColumn.setVisible(isDefaultVisible);
        return subjectGradesColumn;

    }


    private static Tooltip createGradeTooltip(StudentSpecificSubjectGradeModel grade) {
        Tooltip tooltip = new Tooltip();


        String gradeText = ResourceUtil.getMessage(ResourceConst.GRADE_TOOLTIP_TEXT.value());
        String teacherText = ResourceUtil.getMessage(ResourceConst.TEACHER_TOOLTIP_TEXT.value());
        String descriptionText = ResourceUtil.getMessage(DESCRIPTION_TOOLTIP_TEXT.value());


        String formattedText = String.format(gradeText + ": %d, \n" + descriptionText + ": %s, \n" + teacherText + ": %s \n",
                grade.getGrade().getValue(),
                grade.getDescription().getValue(),
                grade.getTeacherName().getValue());

        tooltip.setText(formattedText);
        return tooltip;
    }

}
