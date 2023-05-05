package pl.edziennik.client.rest;

import org.springframework.http.HttpMethod;
import pl.edziennik.client.rest.client.RestClient;
import pl.edziennik.client.rest.client.URLConstants;
import pl.edziennik.client.rest.dto.grade.GradeDto;
import pl.edziennik.client.rest.dto.grade.GradeRequestDto;

public class GradeRestClient {

    private final RestClient restClient;

    public GradeRestClient() {
        this.restClient = RestClient.getInstance();
    }

    public void saveGrade(GradeRequestDto dto, Long studentId, Long subjectId) {
        String url = URLConstants.GRADE_URL + studentId + "/subjects/" + subjectId + "/grades";
        restClient.send(HttpMethod.POST, url, dto);
    }
}
