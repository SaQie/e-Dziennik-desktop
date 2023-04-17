package pl.edziennik.client.rest.dto.teacher;

import lombok.Getter;
import lombok.Setter;
import pl.edziennik.client.rest.dto.DictionaryItemDto;
import pl.edziennik.client.rest.dto.school.SimpleSchoolDto;

@Getter
@Setter
public class TeacherDto implements DictionaryItemDto {

    private Long teacherId;
    private Long userId;

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
    private SimpleSchoolDto school;

    @Override
    public Long getId() {
        return teacherId;
    }

    @Override
    public String getName() {
        return fullName;
    }
}
