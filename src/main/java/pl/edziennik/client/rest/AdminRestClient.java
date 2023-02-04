package pl.edziennik.client.rest;

import org.springframework.http.HttpMethod;
import pl.edziennik.client.controller.model.admin.SchoolLevelComboBoxItem;
import pl.edziennik.client.rest.client.RestClient;
import pl.edziennik.client.rest.client.URLConstants;
import pl.edziennik.client.rest.pojo.*;

import java.util.Arrays;
import java.util.List;

public class AdminRestClient {

    private final RestClient restClient;

    public AdminRestClient() {
        this.restClient = new RestClient();
    }

    public AdminPojo register(AdminPojo adminPojo) {
        return restClient.send(HttpMethod.POST, URLConstants.ADMIN_URL, adminPojo, AdminPojo.class);
    }

    public List<SchoolPojo> getSchoolList() {
        SchoolPojo[] schoolPojos = restClient.send(HttpMethod.GET, URLConstants.SCHOOL_URL, SchoolPojo[].class);
        return Arrays.asList(schoolPojos);
    }


    public List<TeacherPojo> getTeacherList() {
        TeacherPojo[] teacherPojos = restClient.send(HttpMethod.GET, URLConstants.TEACHER_URL, TeacherPojo[].class);
        return Arrays.asList(teacherPojos);
    }

    public List<StudentPojo> getStudentList() {
        StudentPojo[] studentPojos = restClient.send(HttpMethod.GET, URLConstants.STUDENT_URL, StudentPojo[].class);
        return Arrays.asList(studentPojos);
    }

    public List<AdminPojo> getAdminList() {
        AdminPojo[] adminPojos = restClient.send(HttpMethod.GET, URLConstants.ADMIN_URL, AdminPojo[].class);
        return Arrays.asList(adminPojos);
    }

    public SchoolPojo getSchoolPojo(Long id) {
        return restClient.send(HttpMethod.GET, URLConstants.SCHOOL_URL + id, SchoolPojo.class);
    }

    public SchoolPojo saveNewSchool(SchoolPojo schoolPojo) {
        return restClient.send(HttpMethod.POST, URLConstants.SCHOOL_URL, schoolPojo, SchoolPojo.class);
    }

    public SchoolPojo editSchool(Long id, SchoolPojo schoolPojo) {
        return restClient.send(HttpMethod.POST, URLConstants.SCHOOL_URL + id, schoolPojo, SchoolPojo.class);
    }

    public List<SchoolLevelComboBoxItem> loadComboBoxItems() {
        SchoolLevelComboBoxItem[] schoolLevelComboBoxItems = restClient.send(HttpMethod.GET, URLConstants.SCHOOL_LEVELS_URL, SchoolLevelComboBoxItem[].class);
        return Arrays.asList(schoolLevelComboBoxItems);
    }

    public void deleteSchool(Long idSchool) {
        restClient.send(HttpMethod.DELETE, URLConstants.SCHOOL_DELETE_URL, idSchool);
    }

    public List<ConfigurationPojo> getConfigurationList() {
        ConfigurationPojo[] configurationPojos = restClient.send(HttpMethod.GET, URLConstants.CONFIGURATION_URL, ConfigurationPojo[].class);
        return List.of(configurationPojos);
    }


    public void saveConfiguration(SettingsValue value) {
        restClient.send(HttpMethod.PUT, URLConstants.CONFIGURATION_URL + value.getId(), value);
    }


}
