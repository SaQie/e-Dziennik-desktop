package pl.edziennik.client.rest.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCredentialsDto {

    private String username;
    private String password;

}
