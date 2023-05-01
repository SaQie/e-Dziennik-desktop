package pl.edziennik.client.common.controller.columns.student;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.common.model.student.StudentSpecificSubjectGradeModel;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.*;

class StudentSpecificSubjectGradesTableViewColumns {

    private StudentSpecificSubjectGradesTableViewColumns() {
    }


    protected static TableColumn<StudentSpecificSubjectGradeModel, Number> getGradeColumn(final boolean isDefaultVisible) {
        TableColumn<StudentSpecificSubjectGradeModel, Number> gradeColumn = new TableColumn<>(ResourceUtil.getMessage(GRADE_COLUMN_KEY.value()));
        gradeColumn.setCellValueFactory(cellData -> cellData.getValue().getGrade());
        gradeColumn.setVisible(isDefaultVisible);

        gradeColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.valueOf(item));
                    switch (item.intValue()) {
                        case 6 -> setStyle(Styles.GRADE_6_COLOR_STYLE);
                        case 5 -> setStyle(Styles.GRADE_5_COLOR_STYLE);
                        case 4 -> setStyle(Styles.GRADE_4_COLOR_STYLE);
                        case 3 -> setStyle(Styles.GRADE_3_COLOR_STYLE);
                        case 2 -> setStyle(Styles.GRADE_2_COLOR_STYLE);
                        case 1 -> setStyle(Styles.GRADE_1_COLOR_STYLE);
                        default -> setStyle("");
                    }
                }
            }
        });

        return gradeColumn;
    }

    protected static TableColumn<StudentSpecificSubjectGradeModel, Number> getWeightColumn(final boolean isDefaultVisible) {
        TableColumn<StudentSpecificSubjectGradeModel, Number> weightColumn = new TableColumn<>(ResourceUtil.getMessage(WEIGHT_COLUMN_KEY.value()));
        weightColumn.setCellValueFactory(cellData -> cellData.getValue().getGrade());
        weightColumn.setVisible(isDefaultVisible);
        return weightColumn;
    }

    protected static TableColumn<StudentSpecificSubjectGradeModel, String> getDescriptionColumn(final boolean isDefaultVisible) {
        TableColumn<StudentSpecificSubjectGradeModel, String> descriptionColumn = new TableColumn<>(ResourceUtil.getMessage(DESCRIPTION_COLUMN_KEY.value()));
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());
        descriptionColumn.setVisible(isDefaultVisible);
        return descriptionColumn;
    }

    protected static TableColumn<StudentSpecificSubjectGradeModel, String> getCreatedDateColumn(final boolean isDefaultVisible) {
        TableColumn<StudentSpecificSubjectGradeModel, String> createdDateColumn = new TableColumn<>(ResourceUtil.getMessage(CREATED_DATE_COLUMN_KEY.value()));
        createdDateColumn.setCellValueFactory(cellData -> cellData.getValue().getCreatedDate());
        createdDateColumn.setVisible(isDefaultVisible);
        return createdDateColumn;
    }

    protected static TableColumn<StudentSpecificSubjectGradeModel, String> getTeacherNameColumn(final boolean isDefaultVisible) {
        TableColumn<StudentSpecificSubjectGradeModel, String> getTeacherNameColumn = new TableColumn<>(ResourceUtil.getMessage(POSTED_BY_COLUMN_KEY.value()));
        getTeacherNameColumn.setCellValueFactory(cellData -> cellData.getValue().getTeacherName());
        getTeacherNameColumn.setVisible(isDefaultVisible);
        return getTeacherNameColumn;
    }

}
