package pl.edziennik.client.rest.dto.school;

import lombok.Getter;
import lombok.Setter;
import pl.edziennik.client.rest.dto.DictionaryItemDto;
import pl.edziennik.client.rest.dto.schoollevel.SimpleSchoolLevelDto;

@Getter
@Setter
public class SchoolDto implements DictionaryItemDto {

    private Long schoolId;
    private String name;
    private String postalCode;
    private String city;
    private String nip;
    private String regon;
    private String address;
    private String phoneNumber;
    private Long schoolLevelId;
    private SimpleSchoolLevelDto schoolLevel;


    @Override
    public Long getId() {
        return schoolId;
    }
}
