package pl.edziennik.client.rest;

import pl.edziennik.client.rest.client.RestClient;
import pl.edziennik.client.rest.pojo.AdminPojo;
import pl.edziennik.client.rest.pojo.SchoolPojo;

import java.util.Arrays;
import java.util.List;

public class AdminRestClient {

    private RestClient restClient;

    public AdminRestClient() {
        this.restClient = new RestClient();
    }

    public AdminPojo register(AdminPojo adminPojo){
        return restClient.post("http://localhost:8080/api/admins", adminPojo, AdminPojo.class);
    }

    public List<SchoolPojo> getSchoolList(){
        SchoolPojo[] schoolPojos = restClient.get("http://localhost:8080/api/schools", SchoolPojo[].class);
        return Arrays.asList(schoolPojos);
    }
}
