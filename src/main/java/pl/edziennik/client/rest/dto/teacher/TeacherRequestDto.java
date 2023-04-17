package pl.edziennik.client.rest.dto.teacher;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequestDto {

    private Long teacherId;
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
    private Long schoolId;
    private String password;

}
