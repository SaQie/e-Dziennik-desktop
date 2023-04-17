package pl.edziennik.client.rest.dto.parent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentRequestDto {

    private Long parentId;
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String postalCode;
    private String city;
    private String pesel;
    private String email;
    private String phoneNumber;
    private String role;
    private Long studentId;
    private String password;

}
