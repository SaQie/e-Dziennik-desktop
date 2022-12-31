package pl.edziennik.client.rest.common;

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
