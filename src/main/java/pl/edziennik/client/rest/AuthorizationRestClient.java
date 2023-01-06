package pl.edziennik.client.rest;

import pl.edziennik.client.controller.model.admin.SchoolLevelComboBoxItem;
import pl.edziennik.client.rest.client.RestClient;
import pl.edziennik.client.rest.client.URLConstants;
import pl.edziennik.client.rest.pojo.LoginCredentialsPojo;

import java.util.Arrays;
import java.util.List;

public class AuthorizationRestClient {

    private RestClient restClient;

    public AuthorizationRestClient() {
        this.restClient = new RestClient();
    }

    public void login(LoginCredentialsPojo credentialsPojo){
        restClient.login(URLConstants.LOGIN_URL, credentialsPojo);
    }

    public List<SchoolLevelComboBoxItem> loadComboBoxItems(){
        SchoolLevelComboBoxItem[] schoolLevelComboBoxItems = restClient.get(URLConstants.SCHOOL_LEVELS_URL, SchoolLevelComboBoxItem[].class);
        return Arrays.asList(schoolLevelComboBoxItems);
    }
}
