package pl.edziennik.client.rest.dto.schoolclass;

import lombok.Getter;
import lombok.Setter;
import pl.edziennik.client.rest.dto.school.SimpleSchoolDto;
import pl.edziennik.client.rest.dto.teacher.SimpleTeacherDto;

@Getter
@Setter
public class SchoolClassDto {

    private Long id;
    private String className;
    private SimpleTeacherDto supervisingTeacher;
    private SimpleSchoolDto school;

}
