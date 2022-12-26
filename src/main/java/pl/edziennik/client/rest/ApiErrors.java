package pl.edziennik.client.rest;

import lombok.Data;

@Data
public class ApiErrors {

    private String cause;
    private String exceptionType;
    private String fields;

}
