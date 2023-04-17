package pl.edziennik.client.rest.dto.schoolclass;

import lombok.Getter;
import lombok.Setter;
import pl.edziennik.client.rest.dto.DictionaryItemDto;
import pl.edziennik.client.rest.dto.school.SimpleSchoolDto;
import pl.edziennik.client.rest.dto.teacher.SimpleTeacherDto;

@Getter
@Setter
public class SchoolClassDto implements DictionaryItemDto {

    private Long schoolClassId;
    private String className;
    private SimpleTeacherDto supervisingTeacher;
    private SimpleSchoolDto school;

    @Override
    public Long getId() {
        return schoolClassId;
    }

    @Override
    public String getName() {
        return className;
    }
}
