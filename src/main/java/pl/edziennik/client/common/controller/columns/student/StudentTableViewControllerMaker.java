package pl.edziennik.client.common.controller.columns.student;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import pl.edziennik.client.common.model.student.StudentGradeModel;

import java.util.List;

public class StudentTableViewControllerMaker {


    private StudentTableViewControllerMaker() {

    }

    public static StudentAllSubjectGradesViewBuilder getStudentAllSubjectGradesViewBuilder() {
        return new StudentAllSubjectGradesViewBuilder();
    }


    public static class StudentAllSubjectGradesViewBuilder {

        private final List<TableColumn<StudentGradeModel, ?>> columns = FXCollections.observableArrayList();

        public StudentAllSubjectGradesViewBuilder withSubjectIdentifierColumn(final boolean isDefaultVisible) {
            columns.add(StudentGradesTableViewColumns.getSubjectIdentifierColumn(isDefaultVisible));
            return this;
        }

        public StudentAllSubjectGradesViewBuilder withSubjectNameColumn(final boolean isDefaultVisible) {
            columns.add(StudentGradesTableViewColumns.getSubjectNameColumn(isDefaultVisible));
            return this;
        }

        public StudentAllSubjectGradesViewBuilder withSubjectGradesColumn(final boolean isDefaultVisible) {
            columns.add(StudentGradesTableViewColumns.getSubjectGradesColumn(isDefaultVisible));
            return this;
        }

        public List<TableColumn<StudentGradeModel, ?>> build() {
            return columns;
        }

    }

}
