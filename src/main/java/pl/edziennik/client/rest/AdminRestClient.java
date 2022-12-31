package pl.edziennik.client.rest;

import pl.edziennik.client.rest.client.RestClient;
import pl.edziennik.client.rest.pojo.AdminPojo;

public class AdminRestClient {

    private RestClient restClient;

    public AdminRestClient() {
        this.restClient = new RestClient();
    }

    public AdminPojo register(AdminPojo adminPojo){
        return restClient.post("http://localhost:8080/api/admins", adminPojo, AdminPojo.class);
    }
}
