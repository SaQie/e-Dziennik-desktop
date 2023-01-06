package pl.edziennik.client.rest.client.response;

import lombok.Data;

@Data
public class ApiAuthResponse {

    private String username;
    private String message;
    private String token;
    private String refreshToken;

}
