package pl.edziennik.client.rest;

import pl.edziennik.client.rest.client.RestClient;
import pl.edziennik.client.rest.client.URLConstants;
import pl.edziennik.client.rest.pojo.LoginCredentialsPojo;

public class AuthorizationRestClient {

    private RestClient restClient;

    public AuthorizationRestClient() {
        this.restClient = new RestClient();
    }

    public void login(LoginCredentialsPojo credentialsPojo){
        restClient.login(URLConstants.LOGIN_URL, credentialsPojo);
    }
}
