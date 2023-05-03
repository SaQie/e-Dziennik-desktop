package pl.edziennik.client.rest.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edziennik.client.rest.dto.schoolclass.SimpleSchoolClassDto;
import pl.edziennik.client.rest.dto.teacher.SimpleTeacherDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    private Long subjectId;
    private String name;
    private String description;
    private SimpleSchoolClassDto schoolClass;
    private SimpleTeacherDto teacher;

}
