package pl.edziennik.client.rest;

import pl.edziennik.client.rest.client.RestClient;
import pl.edziennik.client.rest.pojo.LoginCredentialsPojo;
import pl.edziennik.client.rest.pojo.TeacherPojo;

public class AuthorizationRestClient {

    private RestClient restClient;

    public AuthorizationRestClient() {
        this.restClient = new RestClient();
    }

    public void login(LoginCredentialsPojo credentialsPojo){
        restClient.login("http://localhost:8080/login", credentialsPojo);
    }
}
