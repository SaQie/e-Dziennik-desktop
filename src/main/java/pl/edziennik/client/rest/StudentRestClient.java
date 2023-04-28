package pl.edziennik.client.rest;

import org.springframework.http.HttpMethod;
import pl.edziennik.client.rest.client.RestClient;
import pl.edziennik.client.rest.client.URLConstants;
import pl.edziennik.client.rest.dto.student.StudentGradeDto;

public class StudentRestClient {

    private final RestClient restClient;

    public StudentRestClient() {
        this.restClient = RestClient.getInstance();
    }


    public StudentGradeDto getAllStudentSubjectGrades(final Long idStudent) {
        return restClient.send(HttpMethod.GET,
                URLConstants.ALL_STUDENT_GRADES_URL + idStudent + "/subjects/grades", StudentGradeDto.class);

    }


}
