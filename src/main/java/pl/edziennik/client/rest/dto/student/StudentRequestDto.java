package pl.edziennik.client.rest.dto.student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDto {

    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String postalCode;
    private String city;
    private String pesel;
    private String email;
    private String phoneNumber;
    private Long schoolId;
    private Long schoolClassId;
    private String password;

}
