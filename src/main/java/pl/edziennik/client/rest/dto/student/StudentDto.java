package pl.edziennik.client.rest.dto.student;

import lombok.Getter;
import lombok.Setter;
import pl.edziennik.client.rest.dto.parent.SimpleParentDto;
import pl.edziennik.client.rest.dto.schoolclass.SimpleSchoolClassDto;
import pl.edziennik.client.rest.dto.school.SimpleSchoolDto;

@Getter
@Setter
public class StudentDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String fullName;
    private String address;
    private String postalCode;
    private String city;
    private String pesel;
    private String email;
    private String password;
    private String phoneNumber;
    private SimpleParentDto parent;
    private SimpleSchoolDto school;
    private SimpleSchoolClassDto schoolClass;
    private String role;

}
