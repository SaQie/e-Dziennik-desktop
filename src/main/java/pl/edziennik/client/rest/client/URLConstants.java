package pl.edziennik.client.rest.client;

public class URLConstants {


    private URLConstants() {
    }

    private static final String API_VERSION = "/v1/";


    public static final String LOGIN_URL = RestClient.BASE_URL + "/login";
    public static final String ADMIN_URL = RestClient.BASE_URL + "/api" + API_VERSION + "admins/";
    public static final String TEACHER_URL = RestClient.BASE_URL + "/api" + API_VERSION + "teachers/";
    public static final String STUDENT_URL = RestClient.BASE_URL + "/api" + API_VERSION + "students/";
    public static final String SCHOOL_URL = RestClient.BASE_URL + "/api" + API_VERSION + "schools/";
    public static final String PARENT_URL = RestClient.BASE_URL + "/api" + API_VERSION + "parents/";
    public static final String SCHOOL_CLASS_URL = RestClient.BASE_URL + "/api" + API_VERSION + "schoolclasses/";
    public static final String SCHOOL_LEVELS_URL = RestClient.BASE_URL + "/api" + API_VERSION + "schoollevels/";
    public static final String CONFIGURATION_URL = RestClient.BASE_URL + "/api" + API_VERSION + "settings/";

    public static String schoolClassBySchoolUrl(Long idSchool) {
        return SCHOOL_CLASS_URL + "?schoolId=" + idSchool + "&";
    }
}
