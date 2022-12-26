package pl.edziennik.client.rest;

import lombok.Data;

@Data
public class TeacherPojo {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String postalCode;
    private String city;
    private String pesel;
    private String phoneNumber;
    private Long idRole;
    private Long idSchool;

}
