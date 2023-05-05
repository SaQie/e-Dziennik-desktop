package pl.edziennik.client.task.grade;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.GradeRestClient;
import pl.edziennik.client.rest.TeacherRestClient;
import pl.edziennik.client.rest.dto.grade.GradeDto;
import pl.edziennik.client.rest.dto.grade.GradeRequestDto;

public class SaveGradeTask extends Task<Void> {

    private final Long studentId;
    private final Long subjectId;
    private final GradeRequestDto gradeDto;
    private final GradeRestClient gradeRestClient;

    public SaveGradeTask(Long studentId, Long subjectId, GradeRequestDto gradeDto) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.gradeDto = gradeDto;
        this.gradeRestClient = new GradeRestClient();
    }

    @Override
    protected Void call() throws Exception {
        gradeRestClient.saveGrade(gradeDto, studentId, subjectId);
        return null;
    }
}
