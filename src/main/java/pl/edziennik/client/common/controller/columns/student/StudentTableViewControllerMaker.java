package pl.edziennik.client.common.controller.columns.student;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import pl.edziennik.client.common.controller.columns.BasicTableViewColumns;
import pl.edziennik.client.common.model.student.StudentSpecificSubjectGradeModel;
import pl.edziennik.client.common.model.student.StudentSubjectModel;

import java.util.List;

public class StudentTableViewControllerMaker {


    private StudentTableViewControllerMaker() {

    }

    public static StudentAllSubjectGradesViewBuilder getStudentAllSubjectGradesViewBuilder() {
        return new StudentAllSubjectGradesViewBuilder();
    }

    public static StudentSpecificSubjectGradesColumnBuilder getStudentSpecificSubjectGradesColumnBuilder() {
        return new StudentSpecificSubjectGradesColumnBuilder();
    }


    public static class StudentAllSubjectGradesViewBuilder extends BasicTableViewColumns {

        private final List<TableColumn<StudentSubjectModel, ?>> columns = FXCollections.observableArrayList();

        public StudentAllSubjectGradesViewBuilder withSubjectIdentifierColumn(final boolean isDefaultVisible) {
            columns.add(StudentSubjectTableViewColumns.getSubjectIdentifierColumn(isDefaultVisible));
            return this;
        }

        public StudentAllSubjectGradesViewBuilder withSubjectNameColumn(final boolean isDefaultVisible) {
            columns.add(StudentSubjectTableViewColumns.getSubjectNameColumn(isDefaultVisible));
            return this;
        }

        public StudentAllSubjectGradesViewBuilder withSubjectGradesColumn(final boolean isDefaultVisible) {
            columns.add(StudentSubjectTableViewColumns.getSubjectGradesColumn(isDefaultVisible));
            return this;
        }

        public StudentAllSubjectGradesViewBuilder withSelectColumn() {
            columns.add(getSelectColumn(true));
            return this;
        }

        public List<TableColumn<StudentSubjectModel, ?>> build() {
            return columns;
        }

    }

    public static class StudentSpecificSubjectGradesColumnBuilder extends BasicTableViewColumns {

        private final List<TableColumn<StudentSpecificSubjectGradeModel, ?>> columns = FXCollections.observableArrayList();

        public StudentSpecificSubjectGradesColumnBuilder withIdentifierColumn(final boolean isDefaultVisible) {
            columns.add(getIdentifierColumn(isDefaultVisible));
            return this;
        }

        public StudentSpecificSubjectGradesColumnBuilder withTeacherNameColumn(final boolean isDefaultVisible) {
            columns.add(StudentSpecificSubjectGradesTableViewColumns.getTeacherNameColumn(isDefaultVisible));
            return this;
        }

        public StudentSpecificSubjectGradesColumnBuilder withGradeColumn(final boolean isDefaultVisible) {
            columns.add(StudentSpecificSubjectGradesTableViewColumns.getGradeColumn(isDefaultVisible));
            return this;
        }

        public StudentSpecificSubjectGradesColumnBuilder withWeightColumn(final boolean isDefaultVisible) {
            columns.add(StudentSpecificSubjectGradesTableViewColumns.getWeightColumn(isDefaultVisible));
            return this;
        }

        public StudentSpecificSubjectGradesColumnBuilder withDescriptionColumn(final boolean isDefaultVisible) {
            columns.add(StudentSpecificSubjectGradesTableViewColumns.getDescriptionColumn(isDefaultVisible));
            return this;
        }

        public StudentSpecificSubjectGradesColumnBuilder withCreatedDateColumn(final boolean isDefaultVisible) {
            columns.add(StudentSpecificSubjectGradesTableViewColumns.getCreatedDateColumn(isDefaultVisible));
            return this;
        }

        public List<TableColumn<StudentSpecificSubjectGradeModel, ?>> build() {
            return columns;
        }
    }

}
