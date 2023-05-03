package pl.edziennik.client.common.controller.column.teacher;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import pl.edziennik.client.common.controller.column.BasicTableViewColumns;
import pl.edziennik.client.common.model.student.StudentSubjectModel;
import pl.edziennik.client.common.model.teacher.TeacherAllStudentGradesForSpecificSubjectModel;
import pl.edziennik.client.common.model.teacher.TeacherSubjectsModel;

import java.util.List;

public class TeacherTableViewControllerMaker {

    private TeacherTableViewControllerMaker() {

    }

    public static TeacherSubjectsColumnBuilder getTeacherSubjectsColumnBuilder() {
        return new TeacherSubjectsColumnBuilder();
    }

    public static TeacherSpecificSubjectAllStudentGradesColumnBuilder getTeacherSpecificSubjectAllStudentGradesColumnBuilder() {
        return new TeacherSpecificSubjectAllStudentGradesColumnBuilder();
    }

    public static class TeacherSpecificSubjectAllStudentGradesColumnBuilder extends BasicTableViewColumns {

        private final List<TableColumn<TeacherAllStudentGradesForSpecificSubjectModel, ?>> columns = FXCollections.observableArrayList();

        public TeacherSpecificSubjectAllStudentGradesColumnBuilder withSelectColumn(final boolean isDefaultVisible) {
            columns.add(getSelectColumn(isDefaultVisible));
            return this;
        }

        public TeacherSpecificSubjectAllStudentGradesColumnBuilder withIdentifierColumn(final boolean isDefaultVisible) {
            columns.add(getIdentifierColumn(isDefaultVisible));
            return this;
        }

        public TeacherSpecificSubjectAllStudentGradesColumnBuilder withSubjectNameColumn(final boolean isDefaultVisible) {
            columns.add(TeacherSpecificSubjectAllStudentGradesViewColumns.getSubjectNameColumn(isDefaultVisible));
            return this;
        }

        public TeacherSpecificSubjectAllStudentGradesColumnBuilder withStudentFullNameColumn(final boolean isDefaultVisible) {
            columns.add(TeacherSpecificSubjectAllStudentGradesViewColumns.getStudentFullNameColumn(isDefaultVisible));
            return this;
        }

        public TeacherSpecificSubjectAllStudentGradesColumnBuilder withSubjectIdentifierColumn(final boolean isDefaultVisible) {
            columns.add(TeacherSpecificSubjectAllStudentGradesViewColumns.getSubjectIdentifierColumn(isDefaultVisible));
            return this;
        }

        public TeacherSpecificSubjectAllStudentGradesColumnBuilder withStudentGradesColumn(final boolean isDefaultVisible) {
            columns.add(TeacherSpecificSubjectAllStudentGradesViewColumns.getStudentGradesColumn(isDefaultVisible));
            return this;
        }

        public List<TableColumn<TeacherAllStudentGradesForSpecificSubjectModel, ?>> build() {
            return columns;
        }


    }


    public static class TeacherSubjectsColumnBuilder extends BasicTableViewColumns {

        private final List<TableColumn<TeacherSubjectsModel, ?>> columns = FXCollections.observableArrayList();

        public TeacherSubjectsColumnBuilder withSelectColumn(final boolean isDefaultVisible) {
            columns.add(getSelectColumn(isDefaultVisible));
            return this;
        }

        public TeacherSubjectsColumnBuilder withSubjectIdentifierColumn(final boolean isDefaultVisible) {
            columns.add(getIdentifierColumn(isDefaultVisible));
            return this;
        }

        public TeacherSubjectsColumnBuilder withSubjectNameColumn(final boolean isDefaultVisible) {
            columns.add(TeacherSubjectsTableViewColumns.getSubjectNameColumn(isDefaultVisible));
            return this;
        }

        public TeacherSubjectsColumnBuilder withDescriptionColumn(final boolean isDefaultVisible) {
            columns.add(TeacherSubjectsTableViewColumns.getDescriptionColumn(isDefaultVisible));
            return this;
        }

        public TeacherSubjectsColumnBuilder withSchoolClassNameColumn(final boolean isDefaultVisible) {
            columns.add(TeacherSubjectsTableViewColumns.getSchoolClassNameColumn(isDefaultVisible));
            return this;
        }

        public TeacherSubjectsColumnBuilder withTeacherNameColumn(final boolean isDefaultVisible) {
            columns.add(TeacherSubjectsTableViewColumns.getTeacherNameColumn(isDefaultVisible));
            return this;
        }

        public List<TableColumn<TeacherSubjectsModel, ?>> build() {
            return columns;
        }

    }

}
