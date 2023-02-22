package pl.edziennik.client.rest.pojo;

import lombok.Data;

@Data
public class TeacherRequestPojo {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String postalCode;
    private String city;
    private String pesel;
    private String phoneNumber;
    private String role;
    private String email;
    private Long idSchool;
    private String password;

}
