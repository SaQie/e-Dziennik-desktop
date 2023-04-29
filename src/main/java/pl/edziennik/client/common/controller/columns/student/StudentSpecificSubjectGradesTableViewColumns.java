package pl.edziennik.client.common.controller.columns.student;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
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

        gradeColumn.setCellFactory(column -> new TableCell<StudentSpecificSubjectGradeModel, Number>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.valueOf(item));
                    switch (item.intValue()) {
                        case 6 -> setStyle("-fx-background-color: rgba(55, 58, 235, 0.5);");
                        case 5 -> setStyle("-fx-background-color: rgba(60, 183, 22, 0.5);");
                        case 4 -> setStyle("-fx-background-color: rgba(138, 231, 99, 0.5);");
                        case 3 -> setStyle("-fx-background-color: rgba(242, 242, 22, 0.5);");
                        case 2 -> setStyle("-fx-background-color: rgba(234, 110, 12, 0.5);");
                        case 1 -> setStyle("-fx-background-color: rgba(218, 17, 28, 0.5);");
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
