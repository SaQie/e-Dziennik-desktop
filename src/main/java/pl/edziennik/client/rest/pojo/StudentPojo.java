package pl.edziennik.client.rest.pojo;

import lombok.Data;

@Data
public class StudentPojo {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String postalCode;
    private String city;
    private String pesel;
    private String email;
    private String parentPhoneNumber;
    private String parentFirstName;
    private String parentLastName;
    private String password;
    private SimpleSchoolPojo school;
    private SimpleSchoolClassPojo schoolClass;
    private String role;

}
