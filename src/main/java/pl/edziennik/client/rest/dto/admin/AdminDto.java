package pl.edziennik.client.rest.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {

    private Long adminId;
    private Long userId;

    private String username;
    private String password;
    private String email;
    private String role;

}
