package pl.edziennik.client.rest;

import org.springframework.http.HttpMethod;
import pl.edziennik.client.controller.model.admin.SchoolLevelComboBoxItem;
import pl.edziennik.client.rest.client.RestClient;
import pl.edziennik.client.rest.client.URLConstants;
import pl.edziennik.client.rest.dto.admin.AdminDto;
import pl.edziennik.client.rest.dto.config.ConfigurationDto;
import pl.edziennik.client.rest.dto.config.SettingsValueDto;
import pl.edziennik.client.rest.dto.school.SchoolDto;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassDto;
import pl.edziennik.client.rest.dto.student.StudentDto;
import pl.edziennik.client.rest.dto.student.StudentRequestDto;
import pl.edziennik.client.rest.dto.teacher.TeacherDto;
import pl.edziennik.client.rest.dto.teacher.TeacherRequestDto;

import java.util.Arrays;
import java.util.List;

public class AdminRestClient {

    private final RestClient restClient;

    public AdminRestClient() {
        this.restClient = RestClient.getInstance();
    }

    public AdminDto register(AdminDto adminDto) {
        return restClient.send(HttpMethod.POST, URLConstants.ADMIN_URL, adminDto, AdminDto.class);
    }

    public List<SchoolDto> getSchoolList() {
        SchoolDto[] send = restClient.send(HttpMethod.GET, URLConstants.SCHOOL_URL, SchoolDto[].class);
        return Arrays.asList(send);
    }

    public List<SchoolClassDto> getSchoolClassesBySchoolId(Long schoolId) {
        SchoolClassDto[] schoolClassResponses = restClient.send(HttpMethod.GET, URLConstants.schoolClassBySchoolUrl(schoolId), SchoolClassDto[].class);
        return Arrays.asList(schoolClassResponses);
    }

    public List<TeacherDto> getTeacherList() {
        TeacherDto[] teacherDtos = restClient.send(HttpMethod.GET, URLConstants.TEACHER_URL, TeacherDto[].class);
        return Arrays.asList(teacherDtos);
    }

    public List<StudentDto> getStudentList() {
        StudentDto[] studentDtos = restClient.send(HttpMethod.GET, URLConstants.STUDENT_URL, StudentDto[].class);
        return Arrays.asList(studentDtos);
    }

    public List<AdminDto> getAdminList() {
        AdminDto[] adminRespons = restClient.send(HttpMethod.GET, URLConstants.ADMIN_URL, AdminDto[].class);
        return Arrays.asList(adminRespons);
    }

    public SchoolDto getSchoolPojo(Long id) {
        return restClient.send(HttpMethod.GET, URLConstants.SCHOOL_URL + id, SchoolDto.class);
    }

    public StudentDto saveNewStudent(StudentRequestDto studentPojo) {
        return restClient.send(HttpMethod.POST, URLConstants.STUDENT_URL, studentPojo, StudentDto.class);
    }

    public SchoolDto saveNewSchool(SchoolDto schoolDto) {
        return restClient.send(HttpMethod.POST, URLConstants.SCHOOL_URL, schoolDto, SchoolDto.class);
    }

    public SchoolDto editSchool(Long id, SchoolDto schoolDto) {
        return restClient.send(HttpMethod.PUT, URLConstants.SCHOOL_URL + id, schoolDto, SchoolDto.class);
    }

    public List<SchoolLevelComboBoxItem> loadComboBoxItems() {
        SchoolLevelComboBoxItem[] schoolLevelComboBoxItems = restClient.send(HttpMethod.GET, URLConstants.SCHOOL_LEVELS_URL, SchoolLevelComboBoxItem[].class);
        return Arrays.asList(schoolLevelComboBoxItems);
    }

    public void deleteSchool(Long idSchool) {
        restClient.send(HttpMethod.DELETE, URLConstants.SCHOOL_URL, idSchool);
    }

    public List<ConfigurationDto> getConfigurationList() {
        ConfigurationDto[] configurationRespons = restClient.send(HttpMethod.GET, URLConstants.CONFIGURATION_URL, ConfigurationDto[].class);
        return List.of(configurationRespons);
    }


    public void saveConfiguration(SettingsValueDto value) {
        restClient.send(HttpMethod.PATCH, URLConstants.CONFIGURATION_URL + value.getId(), value);
    }


    public void deleteStudent(Long idStudent) {
        restClient.send(HttpMethod.DELETE, URLConstants.STUDENT_URL, idStudent);
    }

    public StudentDto getStudent(Long idStudent) {
        return restClient.send(HttpMethod.GET, URLConstants.STUDENT_URL + idStudent, StudentDto.class);
    }

    public StudentDto editStudent(Long idStudent, StudentRequestDto pojo) {
        return restClient.send(HttpMethod.PUT, URLConstants.STUDENT_URL + idStudent, pojo, StudentDto.class);
    }

    public TeacherDto saveNewTeacher(TeacherRequestDto pojo) {
        return restClient.send(HttpMethod.POST, URLConstants.TEACHER_URL, pojo, TeacherDto.class);
    }

    public void deleteTeacher(Long idTeacher) {
        restClient.send(HttpMethod.DELETE, URLConstants.TEACHER_URL, idTeacher);
    }

    public TeacherDto editTeacher(TeacherRequestDto dto, Long id) {
        return restClient.send(HttpMethod.PUT, URLConstants.TEACHER_URL + id, dto, TeacherDto.class);
    }

    public TeacherDto getTeacher(Long idTeacher) {
        return restClient.send(HttpMethod.GET, URLConstants.TEACHER_URL + idTeacher, TeacherDto.class);
    }

    public AdminDto saveNewAdmin(AdminDto dto) {
        return restClient.send(HttpMethod.POST, URLConstants.ADMIN_URL, dto, AdminDto.class);
    }

    public AdminDto editAdmin(Long idAdmin, AdminDto dto) {
        return restClient.send(HttpMethod.PUT, URLConstants.ADMIN_URL + idAdmin, dto, AdminDto.class);
    }

    public AdminDto getAdmin(Long idAdmin) {
        return restClient.send(HttpMethod.GET, URLConstants.ADMIN_URL + idAdmin, AdminDto.class);
    }

    public void deleteAdmin(Long idAdmin) {
        restClient.send(HttpMethod.DELETE, URLConstants.ADMIN_URL, idAdmin);
    }
}
