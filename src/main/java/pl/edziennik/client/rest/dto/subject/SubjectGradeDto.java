package pl.edziennik.client.rest.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edziennik.client.rest.dto.grade.GradeDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectGradeDto {

    private Long subjectId;
    private String name;
    private List<GradeDto> grades;

}
