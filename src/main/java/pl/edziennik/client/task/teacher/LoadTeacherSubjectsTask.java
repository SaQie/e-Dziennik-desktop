package pl.edziennik.client.task.teacher;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.TeacherRestClient;
import pl.edziennik.client.rest.dto.subject.SubjectDto;

import java.util.List;

public class LoadTeacherSubjectsTask extends Task<List<SubjectDto>> {

    private final Long teacherId;
    private final TeacherRestClient restClient;

    public LoadTeacherSubjectsTask(Long teacherId) {
        this.teacherId = teacherId;
        this.restClient = new TeacherRestClient();
    }

    @Override
    protected List<SubjectDto> call() throws Exception {
        return restClient.getTeacherSubjects(teacherId);
    }
}
