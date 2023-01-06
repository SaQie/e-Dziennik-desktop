package pl.edziennik.client.rest.client;

public class URLConstants {



    private URLConstants() {
    }

    public static final String LOGIN_URL = RestClient.BASE_URL + "/login";
    public static final String ADMIN_URL = RestClient.BASE_URL + "/api/admins";
    public static final String SCHOOL_URL =  RestClient.BASE_URL + "/api/schools";
    public static final String SCHOOL_LEVELS_URL = RestClient.BASE_URL + "/api/schoollevels";
}
