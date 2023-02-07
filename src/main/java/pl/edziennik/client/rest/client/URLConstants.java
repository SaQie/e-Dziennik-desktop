package pl.edziennik.client.rest.client;

public class URLConstants {



    private URLConstants() {
    }

    public static final String LOGIN_URL = RestClient.BASE_URL + "/login";
    public static final String ADMIN_URL = RestClient.BASE_URL + "/api/admins/";
    public static final String TEACHER_URL = RestClient.BASE_URL + "/api/teachers/";
    public static final String STUDENT_URL = RestClient.BASE_URL + "/api/students/";
    public static final String SCHOOL_URL =  RestClient.BASE_URL + "/api/schools/";
    public static final String SCHOOL_CLASS_URL =  RestClient.BASE_URL + "/api/schoolclasses/";
    public static final String SCHOOL_DELETE_URL =  RestClient.BASE_URL + "/api/schools/";
    public static final String SCHOOL_LEVELS_URL = RestClient.BASE_URL + "/api/schoollevels/";
    public static final String CONFIGURATION_URL = RestClient.BASE_URL + "/api/settings/";

    public static String schoolClassBySchoolUrl(Long idSchool){
        return SCHOOL_CLASS_URL + "?schoolId="+idSchool;
    }
}
