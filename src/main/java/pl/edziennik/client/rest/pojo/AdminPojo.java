package pl.edziennik.client.rest.pojo;

import lombok.Data;

@Data
public class AdminPojo {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;

}
