package pl.edziennik.client.rest.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRequestPojo {

    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String postalCode;
    private String city;
    private String pesel;
    private String email;
    private String parentFirstName;
    private String parentLastName;
    private String parentPhoneNumber;
    private Long idSchool;
    private Long idSchoolClass;
    private String password;

}
