package pl.edziennik.client.rest.client.response;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private String method;
    private String code;
    private String status;
    private String url;
    private ApiErrors[] errors;
    private String stackTrace;
    private T result;
}
