package pl.edziennik.client.task.teacher;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.TeacherRestClient;
import pl.edziennik.client.rest.dto.student.StudentSpecificSubjectGradeDto;

import java.util.List;

public class LoadAllStudentsSpecificSubjectGradesTask extends Task<List<StudentSpecificSubjectGradeDto>> {

    private final TeacherRestClient restClient;
    private final Long subjectId;

    public LoadAllStudentsSpecificSubjectGradesTask(Long subjectId) {
        this.subjectId = subjectId;
        this.restClient = new TeacherRestClient();
    }

    @Override
    protected List<StudentSpecificSubjectGradeDto> call() throws Exception {
        return restClient.getAllStudentSpecificSubjectGrades(subjectId);
    }
}
