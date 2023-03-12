package pl.edziennik.client.rest.dto.parent;

import lombok.Getter;
import lombok.Setter;
import pl.edziennik.client.rest.dto.student.SimpleStudentDto;

@Getter
@Setter
public class ParentDto {

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
    private String phoneNumber;
    private String role;
    private Long idStudent;
    private SimpleStudentDto student;

}
