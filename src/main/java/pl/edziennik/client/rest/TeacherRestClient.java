package pl.edziennik.client.rest;

import pl.edziennik.client.rest.client.RestClient;

public class TeacherRestClient {

    private RestClient restClient;

    public TeacherRestClient() {
        this.restClient = new RestClient();
    }

    public TeacherPojo get(Long id) {
        return restClient.get("http://localhost:8080/api/teachers/4", TeacherPojo.class);
    }
}
