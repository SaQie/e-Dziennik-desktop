package pl.edziennik.client.rest;

import org.springframework.http.HttpMethod;
import pl.edziennik.client.rest.client.RestClient;
import pl.edziennik.client.rest.client.URLConstants;
import pl.edziennik.client.rest.dto.student.StudentSpecificSubjectGradeDto;
import pl.edziennik.client.rest.dto.student.StudentSubjectsGradeDto;
import pl.edziennik.client.rest.dto.subject.SubjectDto;

import java.util.Arrays;
import java.util.List;

public class TeacherRestClient {

    private final RestClient restClient;

    public TeacherRestClient() {
        this.restClient = RestClient.getInstance();
    }

    public List<SubjectDto> getTeacherSubjects(final Long teacherId) {
        SubjectDto[] dtos = restClient.send(HttpMethod.GET, URLConstants.TEACHER_URL + teacherId + "/subjects", SubjectDto[].class);
        return Arrays.asList(dtos);
    }

    public List<StudentSpecificSubjectGradeDto> getAllStudentSpecificSubjectGrades(Long subjectId) {
        StudentSpecificSubjectGradeDto[] dtos = restClient.send(HttpMethod.GET, URLConstants.STUDENT_URL + "/subjects/" + subjectId + "/grades", StudentSpecificSubjectGradeDto[].class);
        return Arrays.asList(dtos);

    }
}
