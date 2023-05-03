package pl.edziennik.client.task.student;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.StudentRestClient;
import pl.edziennik.client.rest.dto.student.StudentSubjectsGradeDto;

public class LoadAllStudentGradesTask extends Task<StudentSubjectsGradeDto> {

    private final Long idStudent;
    private final StudentRestClient restClient;

    public LoadAllStudentGradesTask(final Long idStudent) {
        this.restClient = new StudentRestClient();
        this.idStudent = idStudent;
    }

    @Override
    protected StudentSubjectsGradeDto call() throws Exception {
        return restClient.getAllStudentSubjectGrades(idStudent);
    }
}
