package pl.edziennik.client.rest;

import pl.edziennik.client.controller.model.admin.SchoolLevelComboBoxItem;
import pl.edziennik.client.rest.client.RestClient;
import pl.edziennik.client.rest.client.URLConstants;
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
        return restClient.post(URLConstants.ADMIN_URL, adminPojo, AdminPojo.class);
    }

    public List<SchoolPojo> getSchoolList(){
        SchoolPojo[] schoolPojos = restClient.get(URLConstants.SCHOOL_URL, SchoolPojo[].class);
        return Arrays.asList(schoolPojos);
    }

    public SchoolPojo saveNewSchool(SchoolPojo schoolPojo){
        return restClient.post(URLConstants.SCHOOL_URL, schoolPojo, SchoolPojo.class);
    }

    public List<SchoolLevelComboBoxItem> loadComboBoxItems(){
        SchoolLevelComboBoxItem[] schoolLevelComboBoxItems = restClient.get(URLConstants.SCHOOL_LEVELS_URL, SchoolLevelComboBoxItem[].class);
        return Arrays.asList(schoolLevelComboBoxItems);
    }

    public void deleteSchool(Long idSchool) {
        restClient.delete(URLConstants.SCHOOL_DELETE_URL, idSchool);
    }
}
