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

    public void setMethod(String method) {
        this.method = method;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setErrors(ApiErrors[] errors) {
        this.errors = errors;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
